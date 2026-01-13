import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  SafeAreaView,
  FlatList,
  TouchableOpacity,
  ScrollView,
} from 'react-native';
import { usePlantStore } from '@store/plantStore';
import { Card, Button } from '@components/index';
import { Colors, Spacing, Typography } from '@theme/tokens';

export const TasksScreen: React.FC = () => {
  const [activeTab, setActiveTab] = useState<'today' | 'upcoming'>('today');
  const todaysTasks = usePlantStore((state) => state.getTodaysTasks());
  const upcomingTasks = usePlantStore((state) => state.getUpcomingTasks());
  const completeTask = usePlantStore((state) => state.completeTask);
  const getPlantById = usePlantStore((state) => state.getPlantById);

  const tasks = activeTab === 'today' ? todaysTasks : upcomingTasks;

  const TaskItem = ({ taskId, plantId, type, dueDate }: { taskId: string; plantId: string; type: string; dueDate: Date }) => {
    const plant = getPlantById(plantId);
    
    return (
      <Card style={styles.taskItem}>
        <TouchableOpacity
          style={styles.taskContent}
          onPress={() => completeTask(taskId)}
        >
          <View style={styles.taskCheckbox}>
            <Text style={styles.taskCheckboxText}>◯</Text>
          </View>
          <View style={{ flex: 1 }}>
            <Text style={styles.taskType}>
              {type === 'water' ? '💧 Water' : '🌱 Fertilize'}
            </Text>
            <Text style={styles.taskPlant}>
              {plant?.name || 'Unknown plant'}
            </Text>
          </View>
          <Text style={styles.taskDate}>
            {new Date(dueDate).toLocaleDateString('en-US', {
              month: 'short',
              day: 'numeric',
            })}
          </Text>
        </TouchableOpacity>
      </Card>
    );
  };

  return (
    <SafeAreaView style={[styles.container, { backgroundColor: Colors.background }]}>
      {/* Header */}
      <View style={styles.header}>
        <Text style={styles.title}>Tasks & Reminders</Text>
      </View>

      {/* Tabs */}
      <View style={styles.tabContainer}>
        <TouchableOpacity
          onPress={() => setActiveTab('today')}
          style={[
            styles.tab,
            activeTab === 'today' && styles.tabActive,
          ]}
        >
          <Text
            style={[
              styles.tabText,
              activeTab === 'today' && styles.tabTextActive,
            ]}
          >
            Today
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          onPress={() => setActiveTab('upcoming')}
          style={[
            styles.tab,
            activeTab === 'upcoming' && styles.tabActive,
          ]}
        >
          <Text
            style={[
              styles.tabText,
              activeTab === 'upcoming' && styles.tabTextActive,
            ]}
          >
            Upcoming
          </Text>
        </TouchableOpacity>
      </View>

      {/* Tasks List */}
      {tasks.length === 0 ? (
        <View style={styles.emptyContainer}>
          <Text style={styles.emptyIcon}>✅</Text>
          <Text style={styles.emptyText}>
            {activeTab === 'today' ? 'All caught up!' : 'No upcoming tasks'}
          </Text>
          <Text style={styles.emptySubtext}>
            {activeTab === 'today'
              ? 'Your plants are happy and well cared for.'
              : 'Add plants to create care tasks.'}
          </Text>
        </View>
      ) : (
        <ScrollView contentContainerStyle={styles.tasksList}>
          {tasks.map((task) => (
            <TaskItem
              key={task.id}
              taskId={task.id}
              plantId={task.plantId}
              type={task.type}
              dueDate={task.scheduledDate || task.dueDate}
            />
          ))}
        </ScrollView>
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
  },
  title: {
    fontSize: Typography.sizes['2xl'],
    fontWeight: '700',
    color: Colors.textPrimary,
  },
  tabContainer: {
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderBottomColor: Colors.border,
    paddingHorizontal: Spacing.lg,
  },
  tab: {
    flex: 1,
    paddingVertical: Spacing.md,
    alignItems: 'center',
  },
  tabActive: {
    borderBottomWidth: 2,
    borderBottomColor: Colors.primary,
  },
  tabText: {
    fontSize: Typography.sizes.base,
    color: Colors.textSecondary,
    fontWeight: '500',
  },
  tabTextActive: {
    color: Colors.primary,
  },
  tasksList: {
    paddingHorizontal: Spacing.lg,
    paddingVertical: Spacing.md,
  },
  taskItem: {
    marginBottom: Spacing.md,
  },
  taskContent: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: Spacing.md,
  },
  taskCheckbox: {
    width: 24,
    height: 24,
    borderRadius: 12,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: Colors.surface,
  },
  taskCheckboxText: {
    fontSize: 16,
    color: Colors.textSecondary,
  },
  taskType: {
    fontSize: Typography.sizes.base,
    fontWeight: '600',
    color: Colors.textPrimary,
  },
  taskPlant: {
    fontSize: Typography.sizes.sm,
    color: Colors.textSecondary,
    marginTop: Spacing.xs,
  },
  taskDate: {
    fontSize: Typography.sizes.sm,
    color: Colors.textSecondary,
    fontWeight: '500',
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
