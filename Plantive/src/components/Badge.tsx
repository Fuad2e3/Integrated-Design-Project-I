import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { Colors, Spacing, Typography } from '@theme/tokens';

interface HealthBadgeProps {
  status: 'healthy' | 'warning' | 'urgent';
  label?: string;
}

export const HealthBadge: React.FC<HealthBadgeProps> = ({
  status,
  label,
}) => {
  const colors = {
    healthy: Colors.healthy,
    warning: Colors.warning,
    urgent: Colors.urgent,
  };

  const labels = {
    healthy: 'Healthy',
    warning: 'Needs care',
    urgent: 'Urgent attention',
  };

  return (
    <View
      style={[
        styles.badge,
        { backgroundColor: colors[status] + '20' },
      ]}
    >
      <View
        style={[
          styles.dot,
          { backgroundColor: colors[status] },
        ]}
      />
      <Text style={[styles.label, { color: colors[status] }]}>
        {label || labels[status]}
      </Text>
    </View>
  );
};

interface IconTextProps {
  icon: string;
  text: string;
}

export const IconText: React.FC<IconTextProps> = ({ icon, text }) => (
  <View style={styles.iconText}>
    <Text style={styles.icon}>{icon}</Text>
    <Text style={styles.text}>{text}</Text>
  </View>
);

const styles = StyleSheet.create({
  badge: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: Spacing.sm,
    paddingVertical: Spacing.xs,
    borderRadius: 4,
    alignSelf: 'flex-start',
  },
  dot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    marginRight: Spacing.xs,
  },
  label: {
    fontSize: Typography.sizes.sm,
    fontWeight: '500',
  },
  iconText: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: Spacing.sm,
  },
  icon: {
    fontSize: 20,
  },
  text: {
    fontSize: Typography.sizes.sm,
    color: Colors.textSecondary,
  },
});
