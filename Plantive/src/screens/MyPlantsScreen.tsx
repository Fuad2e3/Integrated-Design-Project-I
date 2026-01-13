import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  SafeAreaView,
  FlatList,
  TouchableOpacity,
} from 'react-native';
import { usePlantStore } from '@store/plantStore';
import { Card, HealthBadge, Button } from '@components/index';
import { Colors, Spacing, Typography, BorderRadius } from '@theme/tokens';

interface MyPlantsScreenProps {
  onAddPlant: () => void;
  onSelectPlant: (plantId: string) => void;
}

export const MyPlantsScreen: React.FC<MyPlantsScreenProps> = ({
  onAddPlant,
  onSelectPlant,
}) => {
  const plants = usePlantStore((state) => state.plants);
  const [filter, setFilter] = React.useState<'all' | 'indoor' | 'outdoor' | 'healthy' | 'needs-care'>('all');

  const filteredPlants = plants.filter((plant) => {
    if (filter === 'indoor' || filter === 'outdoor') {
      return plant.location === filter;
    }
    if (filter === 'healthy') {
      return plant.healthStatus === 'healthy';
    }
    if (filter === 'needs-care') {
      return plant.healthStatus !== 'healthy';
    }
    return true;
  });

  const FilterButton = ({ label, value }: { label: string; value: typeof filter }) => (
    <TouchableOpacity
      onPress={() => setFilter(value)}
      style={[
        styles.filterButton,
        filter === value && { backgroundColor: Colors.primary },
      ]}
    >
      <Text
        style={[
          styles.filterText,
          filter === value && { color: Colors.textLight },
        ]}
      >
        {label}
      </Text>
    </TouchableOpacity>
  );

  return (
    <SafeAreaView style={[styles.container, { backgroundColor: Colors.background }]}>
      {/* Header */}
      <View style={styles.header}>
        <Text style={styles.title}>My Plants</Text>
        <Button label="+ Add" onPress={onAddPlant} size="sm" />
      </View>

      {/* Filters */}
      <View style={styles.filters}>
        <FilterButton label="All" value="all" />
        <FilterButton label="Indoor" value="indoor" />
        <FilterButton label="Outdoor" value="outdoor" />
        <FilterButton label="Healthy" value="healthy" />
        <FilterButton label="Needs care" value="needs-care" />
      </View>

      {/* Plants Grid */}
      {filteredPlants.length === 0 ? (
        <View style={styles.emptyContainer}>
          <Text style={styles.emptyIcon}>🌱</Text>
          <Text style={styles.emptyText}>No plants found</Text>
          <Text style={styles.emptySubtext}>
            Add your first plant to get started!
          </Text>
          <Button
            label="Add Plant"
            onPress={onAddPlant}
            style={{ marginTop: Spacing.lg }}
          />
        </View>
      ) : (
        <FlatList
          data={filteredPlants}
          numColumns={2}
          keyExtractor={(item) => item.id}
          columnWrapperStyle={styles.row}
          contentContainerStyle={styles.grid}
          scrollEnabled={true}
          renderItem={({ item: plant }) => (
            <TouchableOpacity
              onPress={() => onSelectPlant(plant.id)}
              style={styles.plantCardContainer}
            >
              <Card style={styles.plantCard}>
                <View style={styles.plantImageContainer}>
                  <Text style={styles.plantImageEmoji}>🌿</Text>
                </View>
                <Text style={styles.plantName} numberOfLines={2}>
                  {plant.name}
                </Text>
                <HealthBadge status={plant.healthStatus} />
              </Card>
            </TouchableOpacity>
          )}
        />
      )}
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    paddingHorizontal: Spacing.lg,
    paddingVertical: Spacing.md,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  title: {
    fontSize: Typography.sizes['2xl'],
    fontWeight: '700',
    color: Colors.textPrimary,
  },
  filters: {
    paddingHorizontal: Spacing.lg,
    marginBottom: Spacing.md,
    gap: Spacing.sm,
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  filterButton: {
    paddingHorizontal: Spacing.md,
    paddingVertical: Spacing.sm,
    borderRadius: BorderRadius.full,
    backgroundColor: Colors.surface,
    borderWidth: 1,
    borderColor: Colors.border,
  },
  filterText: {
    fontSize: Typography.sizes.sm,
    color: Colors.textPrimary,
    fontWeight: '500',
  },
  grid: {
    paddingHorizontal: Spacing.lg,
    paddingBottom: Spacing.lg,
  },
  row: {
    justifyContent: 'space-between',
    marginBottom: Spacing.md,
  },
  plantCardContainer: {
    width: '48%',
  },
  plantCard: {
    padding: 0,
    overflow: 'hidden',
  },
  plantImageContainer: {
    width: '100%',
    aspectRatio: 1,
    backgroundColor: Colors.surface,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: Spacing.sm,
  },
  plantImageEmoji: {
    fontSize: 48,
  },
  plantName: {
    fontSize: Typography.sizes.base,
    fontWeight: '600',
    color: Colors.textPrimary,
    marginBottom: Spacing.sm,
    paddingHorizontal: Spacing.md,
  },
  emptyContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: Spacing.lg,
  },
  emptyIcon: {
    fontSize: 64,
    marginBottom: Spacing.md,
  },
  emptyText: {
    fontSize: Typography.sizes.lg,
    fontWeight: '600',
    color: Colors.textPrimary,
    marginBottom: Spacing.sm,
  },
  emptySubtext: {
    fontSize: Typography.sizes.base,
    color: Colors.textSecondary,
    textAlign: 'center',
  },
});
