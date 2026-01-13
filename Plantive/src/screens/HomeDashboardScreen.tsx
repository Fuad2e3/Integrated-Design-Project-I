import React, { useState, useEffect } from 'react';
import {
  View,
  ScrollView,
  Text,
  StyleSheet,
  SafeAreaView,
  FlatList,
} from 'react-native';
import { usePlantStore, Plant, Task } from '@store/plantStore';
import { Card, Button, HealthBadge, IconText } from '@components/index';
import { Colors, Spacing, Typography } from '@theme/tokens';

interface HomeDashboardScreenProps {
  onNavigateToPlants: () => void;
  onNavigateToTasks: () => void;
}

export const HomeDashboardScreen: React.FC<HomeDashboardScreenProps> = ({
  onNavigateToPlants,
  onNavigateToTasks,
}) => {
  const plants = usePlantStore((state) => state.plants);
  const tasks = usePlantStore((state) => state.getTodaysTasks());
  const [weatherInfo] = useState('🌤️ 72°F, Sunny');

  const getGreeting = () => {
    const hour = new Date().getHours();
    if (hour < 12) return 'Good morning';
    if (hour < 18) return 'Good afternoon';
    return 'Good evening';
  };

  const healthySummary = plants.filter((p) => p.healthStatus === 'healthy')
    .length;
  const warningCount = plants.filter((p) => p.healthStatus === 'warning')
    .length;
  const urgentCount = plants.filter((p) => p.healthStatus === 'urgent')
    .length;

  return (
    <SafeAreaView style={[styles.container, { backgroundColor: Colors.background }]}>
      <ScrollView showsVerticalScrollIndicator={false}>
        {/* Header */}
        <View style={styles.header}>
          <Text style={styles.greeting}>{getGreeting()}! 👋</Text>
          <Text style={styles.weather}>{weatherInfo}</Text>
        </View>

        {/* Plant Status Summary */}
        <View style={styles.summaryRow}>
          <Card style={styles.summaryCard}>
            <Text style={styles.summaryNumber}>{healthySummary}</Text>
            <Text style={styles.summaryLabel}>Healthy</Text>
          </Card>
          <Card style={styles.summaryCard}>
            <Text style={[styles.summaryNumber, { color: Colors.warning }]}>
              {warningCount}
            </Text>
            <Text style={styles.summaryLabel}>Needs care</Text>
          </Card>
          <Card style={styles.summaryCard}>
            <Text style={[styles.summaryNumber, { color: Colors.urgent }]}>
              {urgentCount}
            </Text>
            <Text style={styles.summaryLabel}>Urgent</Text>
          </Card>
        </View>

        {/* Today's Tasks */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Text style={styles.sectionTitle}>Today's Tasks</Text>
            {tasks.length > 0 && (
              <Button
                label="View all"
                onPress={onNavigateToTasks}
                variant="ghost"
                size="sm"
              />
            )}
          </View>

          {tasks.length === 0 ? (
            <Text style={styles.emptyState}>
              ✅ All caught up! Your plants are happy.
            </Text>
          ) : (
            tasks.map((task) => (
              <Card key={task.id} style={styles.taskCard}>
                <View style={styles.taskContent}>
                  <Text style={styles.taskIcon}>
                    {task.type === 'water' ? '💧' : '🌱'}
                  </Text>
                  <View style={{ flex: 1 }}>
                    <Text style={styles.taskText}>
                      {task.type === 'water' ? 'Water ' : 'Fertilize '}
                      {usePlantStore
                        .getState()
                        .getPlantById(task.plantId)?.name || 'Plant'}
                    </Text>
                  </View>
                </View>
              </Card>
            ))
          )}
        </View>

        {/* Plants Preview */}
        <View style={styles.section}>
          <View style={styles.sectionHeader}>
            <Text style={styles.sectionTitle}>Your Plants</Text>
            <Button
              label={plants.length > 0 ? 'See all' : 'Add plant'}
              onPress={onNavigateToPlants}
              variant="ghost"
              size="sm"
            />
          </View>

          {plants.length === 0 ? (
            <Text style={styles.emptyState}>
              No plants yet. Add your first plant to get started! 🌿
            </Text>
          ) : (
            plants.slice(0, 2).map((plant) => (
              <Card key={plant.id} style={styles.plantPreview}>
                <Text style={styles.plantName}>{plant.name}</Text>
                <HealthBadge status={plant.healthStatus} />
              </Card>
            ))
          )}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    paddingHorizontal: Spacing.lg,
    paddingTop: Spacing.lg,
    paddingBottom: Spacing.md,
  },
  greeting: {
    fontSize: Typography.sizes['2xl'],
    fontWeight: '700',
    color: Colors.textPrimary,
    marginBottom: Spacing.xs,
  },
  weather: {
    fontSize: Typography.sizes.base,
    color: Colors.textSecondary,
  },
  summaryRow: {
    flexDirection: 'row',
    paddingHorizontal: Spacing.lg,
    gap: Spacing.md,
    marginVertical: Spacing.md,
  },
  summaryCard: {
    flex: 1,
    alignItems: 'center',
    paddingVertical: Spacing.md,
  },
  summaryNumber: {
    fontSize: Typography.sizes['2xl'],
    fontWeight: '700',
    color: Colors.healthy,
  },
  summaryLabel: {
    fontSize: Typography.sizes.xs,
    color: Colors.textSecondary,
    marginTop: Spacing.xs,
  },
  section: {
    paddingHorizontal: Spacing.lg,
    marginVertical: Spacing.md,
  },
  sectionHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: Spacing.md,
  },
  sectionTitle: {
    fontSize: Typography.sizes.lg,
    fontWeight: '600',
    color: Colors.textPrimary,
  },
  emptyState: {
    textAlign: 'center',
    color: Colors.textSecondary,
    fontSize: Typography.sizes.base,
    paddingVertical: Spacing.lg,
  },
  taskCard: {
    marginBottom: Spacing.sm,
  },
  taskContent: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: Spacing.md,
  },
  taskIcon: {
    fontSize: 24,
  },
  taskText: {
    fontSize: Typography.sizes.base,
    color: Colors.textPrimary,
    fontWeight: '500',
  },
  plantPreview: {
    marginBottom: Spacing.sm,
    paddingVertical: Spacing.md,
  },
  plantName: {
    fontSize: Typography.sizes.base,
    fontWeight: '600',
    color: Colors.textPrimary,
    marginBottom: Spacing.sm,
  },
});
