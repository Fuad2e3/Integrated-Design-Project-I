import React from 'react';
import { View, Text, StyleSheet, ViewStyle } from 'react-native';
import { Colors, Spacing, BorderRadius, Typography, Shadows } from '@theme/tokens';

interface CardProps {
  children: React.ReactNode;
  style?: ViewStyle;
  variant?: 'default' | 'elevated';
}

export const Card: React.FC<CardProps> = ({ children, style, variant = 'default' }) => {
  const shadowStyle = variant === 'elevated' ? Shadows.md : Shadows.sm;

  return (
    <View
      style={[
        styles.container,
        shadowStyle,
        {
          backgroundColor: Colors.background,
        },
        style,
      ]}
    >
      {children}
    </View>
  );
};

interface PlantCardProps {
  name: string;
  health: 'healthy' | 'warning' | 'urgent';
  image?: string;
  needsWater?: boolean;
  onPress?: () => void;
}

export const PlantCard: React.FC<PlantCardProps> = ({
  name,
  health,
  image,
  needsWater,
  onPress,
}) => {
  const healthColor = {
    healthy: Colors.healthy,
    warning: Colors.warning,
    urgent: Colors.urgent,
  }[health];

  return (
    <Card style={styles.plantCard}>
      <View style={styles.imageContainer}>
        {image ? (
          <Text style={styles.imagePlaceholder}>🌿</Text>
        ) : (
          <Text style={styles.imagePlaceholder}>🌱</Text>
        )}
      </View>
      <View style={styles.plantInfo}>
        <Text style={styles.plantName} numberOfLines={2}>
          {name}
        </Text>
        <View style={styles.healthIndicator}>
          <View
            style={[
              styles.healthDot,
              { backgroundColor: healthColor },
            ]}
          />
          <Text style={styles.healthText}>
            {health === 'healthy' ? 'Healthy' : health === 'warning' ? 'Needs care' : 'Urgent'}
          </Text>
        </View>
        {needsWater && (
          <Text style={styles.waterBadge}>💧 Water today</Text>
        )}
      </View>
    </Card>
  );
};

const styles = StyleSheet.create({
  container: {
    backgroundColor: Colors.background,
    borderRadius: BorderRadius.lg,
    padding: Spacing.md,
  },
  plantCard: {
    marginVertical: Spacing.sm,
  },
  imageContainer: {
    width: '100%',
    height: 150,
    backgroundColor: Colors.surface,
    borderRadius: BorderRadius.md,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: Spacing.md,
  },
  imagePlaceholder: {
    fontSize: 48,
  },
  plantInfo: {
    gap: Spacing.sm,
  },
  plantName: {
    fontSize: Typography.sizes.lg,
    fontWeight: '600',
    color: Colors.textPrimary,
  },
  healthIndicator: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: Spacing.xs,
  },
  healthDot: {
    width: 8,
    height: 8,
    borderRadius: BorderRadius.full,
  },
  healthText: {
    fontSize: Typography.sizes.sm,
    color: Colors.textSecondary,
  },
  waterBadge: {
    fontSize: Typography.sizes.sm,
    color: Colors.urgent,
    fontWeight: '500',
    marginTop: Spacing.xs,
  },
});
