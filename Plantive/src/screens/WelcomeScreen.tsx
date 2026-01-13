import React, { useState } from 'react';
import { View, ScrollView, Text, StyleSheet, SafeAreaView } from 'react-native';
import { Button, PlantCard } from '@components/index';
import { usePlantStore } from '@store/plantStore';
import { Colors, Spacing, Typography } from '@theme/tokens';

interface WelcomeScreenProps {
  onGetStarted: () => void;
  onLogin: () => void;
}

export const WelcomeScreen: React.FC<WelcomeScreenProps> = ({
  onGetStarted,
  onLogin,
}) => {
  const [currentCard, setCurrentCard] = useState(0);

  const benefits = [
    {
      icon: '🌿',
      title: 'Add Plants',
      description: 'Keep track of all your plants in one place',
    },
    {
      icon: '🔔',
      title: 'Get Reminders',
      description: 'Never forget to water or care for your plants',
    },
    {
      icon: '🤖',
      title: 'Ask AI',
      description: 'Get plant care advice tailored to your plants',
    },
  ];

  return (
    <SafeAreaView style={[styles.container, { backgroundColor: Colors.background }]}>
      <ScrollView contentContainerStyle={styles.content}>
        <View style={styles.header}>
          <Text style={styles.logo}>🌱</Text>
          <Text style={styles.appName}>Plantive</Text>
          <Text style={styles.tagline}>
            Plant care made simple, calm, and mistake-free
          </Text>
        </View>

        <View style={styles.benefitCard}>
          <Text style={styles.benefitIcon}>
            {benefits[currentCard].icon}
          </Text>
          <Text style={styles.benefitTitle}>
            {benefits[currentCard].title}
          </Text>
          <Text style={styles.benefitDescription}>
            {benefits[currentCard].description}
          </Text>
        </View>

        <View style={styles.dots}>
          {benefits.map((_, idx) => (
            <View
              key={idx}
              style={[
                styles.dot,
                {
                  backgroundColor:
                    idx === currentCard ? Colors.primary : Colors.border,
                },
              ]}
            />
          ))}
        </View>

        <View style={styles.buttons}>
          <Button
            label="Get Started"
            onPress={onGetStarted}
            fullWidth
            size="lg"
          />
          <Button
            label="Login"
            onPress={onLogin}
            variant="ghost"
            fullWidth
            size="lg"
          />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  content: {
    flexGrow: 1,
    padding: Spacing.lg,
    justifyContent: 'space-around',
  },
  header: {
    alignItems: 'center',
    marginVertical: Spacing.xl,
  },
  logo: {
    fontSize: 64,
    marginBottom: Spacing.md,
  },
  appName: {
    fontSize: Typography.sizes['3xl'],
    fontWeight: '700',
    color: Colors.primary,
    marginBottom: Spacing.sm,
  },
  tagline: {
    fontSize: Typography.sizes.base,
    color: Colors.textSecondary,
    textAlign: 'center',
    maxWidth: 280,
  },
  benefitCard: {
    alignItems: 'center',
    paddingVertical: Spacing.xl,
  },
  benefitIcon: {
    fontSize: 80,
    marginBottom: Spacing.md,
  },
  benefitTitle: {
    fontSize: Typography.sizes.xl,
    fontWeight: '600',
    color: Colors.textPrimary,
    marginBottom: Spacing.sm,
  },
  benefitDescription: {
    fontSize: Typography.sizes.base,
    color: Colors.textSecondary,
    textAlign: 'center',
    maxWidth: 250,
  },
  dots: {
    flexDirection: 'row',
    justifyContent: 'center',
    gap: Spacing.sm,
    marginVertical: Spacing.lg,
  },
  dot: {
    width: 8,
    height: 8,
    borderRadius: 4,
  },
  buttons: {
    gap: Spacing.md,
    marginVertical: Spacing.lg,
  },
});
