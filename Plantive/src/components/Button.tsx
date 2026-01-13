import React from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  ViewStyle,
  TextStyle,
} from 'react-native';
import { Colors, Spacing, BorderRadius, Typography, Shadows } from '@theme/tokens';

interface ButtonProps {
  label: string;
  onPress: () => void;
  variant?: 'primary' | 'secondary' | 'ghost';
  size?: 'sm' | 'md' | 'lg';
  fullWidth?: boolean;
  disabled?: boolean;
  style?: ViewStyle;
}

export const Button: React.FC<ButtonProps> = ({
  label,
  onPress,
  variant = 'primary',
  size = 'md',
  fullWidth = false,
  disabled = false,
  style,
}) => {
  const getContainerStyle = (): ViewStyle => {
    const baseStyle: ViewStyle = {
      borderRadius: BorderRadius.md,
      alignItems: 'center',
      justifyContent: 'center',
      ...Shadows.sm,
    };

    const sizes = {
      sm: { paddingHorizontal: Spacing.sm, paddingVertical: Spacing.xs },
      md: { paddingHorizontal: Spacing.md, paddingVertical: Spacing.sm },
      lg: { paddingHorizontal: Spacing.lg, paddingVertical: Spacing.md },
    };

    const variants = {
      primary: {
        backgroundColor: Colors.primary,
      },
      secondary: {
        backgroundColor: Colors.secondary,
      },
      ghost: {
        backgroundColor: 'transparent',
      },
    };

    return {
      ...baseStyle,
      ...sizes[size],
      ...variants[variant],
      width: fullWidth ? '100%' : 'auto',
      opacity: disabled ? 0.5 : 1,
    };
  };

  const getTextStyle = (): TextStyle => {
    const variants = {
      primary: { color: Colors.textLight },
      secondary: { color: Colors.textLight },
      ghost: { color: Colors.primary },
    };

    return {
      fontSize: Typography.sizes.base,
      fontWeight: '600',
      ...variants[variant],
    };
  };

  return (
    <TouchableOpacity
      onPress={onPress}
      disabled={disabled}
      style={[getContainerStyle(), style]}
      activeOpacity={0.8}
    >
      <Text style={getTextStyle()}>{label}</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    borderRadius: BorderRadius.md,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
