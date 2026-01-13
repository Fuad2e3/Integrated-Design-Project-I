import React from 'react';
import { View, Text, StyleSheet, SafeAreaView, ScrollView } from 'react-native';
import { Button, Card, HealthBadge, IconText } from '@components/index';
import { usePlantStore } from '@store/plantStore';
import { Colors, Spacing, Typography } from '@theme/tokens';

interface PlantDetailsScreenProps {
  plantId: string;
  onGoBack: () => void;
  onWater: () => void;
  onFertilize: () => void;
}

export const PlantDetailsScreen: React.FC<PlantDetailsScreenProps> = ({
  plantId,
  onGoBack,
  onWater,
  onFertilize,
}) => {
  const plant = usePlantStore((state) => state.getPlantById(plantId));

  if (!plant) {
    return (
      <SafeAreaView style={[styles.container, { backgroundColor: Colors.background }]}>
        <Text style={styles.error}>Plant not found</Text>
        <Button label="Go back" onPress={onGoBack} />
      </SafeAreaView>
    );
  }

  const daysUntilWater = Math.ceil(
    (new Date(plant.lastWatered).getTime() +
      plant.wateringFrequencyDays * 86400000 -
      new Date().getTime()) /
      86400000
  );

  return (
    <SafeAreaView style={[styles.container, { backgroundColor: Colors.background }]}>
      <ScrollView showsVerticalScrollIndicator={false}>
        {/* Plant Image */}
        <View style={styles.imageContainer}>
          <Text style={styles.image}>🌿</Text>
        </View>

        {/* Plant Name & Status */}
        <View style={styles.header}>
          <Text style={styles.plantName}>{plant.name}</Text>
          <Text style={styles.scientificName}>{plant.scientificName}</Text>
          <HealthBadge status={plant.healthStatus} style={{ marginTop: Spacing.md }} />
        </View>

        {/* Quick Actions */}
        <View style={styles.actionsContainer}>
          <Button
            label="💧 Water"
            onPress={onWater}
            fullWidth
            size="lg"
          />
          <Button
            label="🌱 Fertilize"
            onPress={onFertilize}
            variant="secondary"
            fullWidth
            size="lg"
          />
        </View>

        {/* Care Info */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Care Info</Text>
          <Card>
            <IconText
              icon="💧"
              text={`Water every ${plant.wateringFrequencyDays} days`}
            />
            <View style={styles.divider} />
            <IconText
              icon="🌱"
              text={`Fertilize every ${plant.fertilizeFrequencyDays} days`}
            />
            <View style={styles.divider} />
            <IconText
              icon="📍"
              text={plant.location === 'indoor' ? 'Indoor plant' : 'Outdoor plant'}
            />
          </Card>
        </View>

        {/* Care Timeline */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Care Timeline</Text>
          <Card>
            <View style={styles.timelineItem}>
              <Text style={styles.timelineIcon}>💧</Text>
              <View style={{ flex: 1 }}>
                <Text style={styles.timelineLabel}>Last watered</Text>
                <Text style={styles.timelineDate}>
                  {new Date(plant.lastWatered).toLocaleDateString()}
                </Text>
              </View>
              <Text style={styles.timelineBadge}>
                {daysUntilWater > 0 ? `In ${daysUntilWater}d` : 'Due today'}
              </Text>
            </View>
            <View style={styles.divider} />
            <View style={styles.timelineItem}>
              <Text style={styles.timelineIcon}>🌱</Text>
              <View style={{ flex: 1 }}>
                <Text style={styles.timelineLabel}>Last fertilized</Text>
                <Text style={styles.timelineDate}>
                  {new Date(plant.lastFertilized).toLocaleDateString()}
                </Text>
              </View>
            </View>
          </Card>
        </View>

        {/* Notes */}
        {plant.notes && (
          <View style={styles.section}>
            <Text style={styles.sectionTitle}>Notes</Text>
            <Card>
              <Text style={styles.notesText}>{plant.notes}</Text>
            </Card>
          </View>
        )}

        <Button label="← Go back" onPress={onGoBack} variant="ghost" fullWidth />
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  imageContainer: {
    width: '100%',
    height: 300,
    backgroundColor: Colors.surface,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    fontSize: 120,
  },
  header: {
    paddingHorizontal: Spacing.lg,
    paddingVertical: Spacing.lg,
  },
  plantName: {
    fontSize: Typography.sizes['2xl'],
    fontWeight: '700',
    color: Colors.textPrimary,
    marginBottom: Spacing.sm,
  },
  scientificName: {
    fontSize: Typography.sizes.base,
    color: Colors.textSecondary,
    fontStyle: 'italic',
  },
  actionsContainer: {
    paddingHorizontal: Spacing.lg,
    gap: Spacing.md,
    marginVertical: Spacing.md,
  },
  section: {
    paddingHorizontal: Spacing.lg,
    marginVertical: Spacing.md,
  },
  sectionTitle: {
    fontSize: Typography.sizes.lg,
    fontWeight: '600',
    color: Colors.textPrimary,
    marginBottom: Spacing.md,
  },
  divider: {
    height: 1,
    backgroundColor: Colors.border,
    marginVertical: Spacing.md,
  },
  timelineItem: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: Spacing.md,
  },
  timelineIcon: {
    fontSize: 24,
  },
  timelineLabel: {
    fontSize: Typography.sizes.sm,
    color: Colors.textSecondary,
  },
  timelineDate: {
    fontSize: Typography.sizes.base,
    fontWeight: '500',
    color: Colors.textPrimary,
    marginTop: Spacing.xs,
  },
  timelineBadge: {
    fontSize: Typography.sizes.sm,
    fontWeight: '600',
    color: Colors.warning,
  },
  notesText: {
    fontSize: Typography.sizes.base,
    color: Colors.textPrimary,
    lineHeight: 20,
  },
  error: {
    fontSize: Typography.sizes.base,
    color: Colors.textPrimary,
    textAlign: 'center',
    marginVertical: Spacing.lg,
  },
});
