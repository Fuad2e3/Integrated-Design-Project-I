import React, { useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import {
  WelcomeScreen,
  HomeDashboardScreen,
  MyPlantsScreen,
  PlantDetailsScreen,
  TasksScreen,
} from '@screens/index';
import { Colors } from '@theme/tokens';

const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

const HomeStack = () => {
  const [selectedPlantId, setSelectedPlantId] = useState<string | null>(null);

  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        animationEnabled: true,
      }}
    >
      <Stack.Screen
        name="HomeDashboard"
        options={{ title: 'Home' }}
      >
        {(props) => (
          <HomeDashboardScreen
            {...props}
            onNavigateToPlants={() => props.navigation.navigate('MyPlants')}
            onNavigateToTasks={() => props.navigation.navigate('Tasks')}
          />
        )}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

const PlantsStack = () => {
  const [selectedPlantId, setSelectedPlantId] = useState<string | null>(null);

  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        animationEnabled: true,
      }}
    >
      <Stack.Screen name="PlantsList" options={{ title: 'My Plants' }}>
        {(props) => (
          <MyPlantsScreen
            {...props}
            onAddPlant={() => {
              // TODO: Implement add plant flow
            }}
            onSelectPlant={(plantId) => {
              setSelectedPlantId(plantId);
              props.navigation.navigate('PlantDetails', { plantId });
            }}
          />
        )}
      </Stack.Screen>
      <Stack.Screen
        name="PlantDetails"
        options={{ title: 'Plant Details' }}
      >
        {(props) => (
          <PlantDetailsScreen
            {...props}
            plantId={selectedPlantId || ''}
            onGoBack={() => props.navigation.goBack()}
            onWater={() => {
              // TODO: Implement water action
            }}
            onFertilize={() => {
              // TODO: Implement fertilize action
            }}
          />
        )}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

const AppTabs = () => {
  return (
    <Tab.Navigator
      screenOptions={{
        headerShown: false,
        tabBarActiveTintColor: Colors.primary,
        tabBarInactiveTintColor: Colors.textSecondary,
        tabBarStyle: {
          borderTopColor: Colors.border,
          backgroundColor: Colors.background,
        },
      }}
    >
      <Tab.Screen
        name="Home"
        component={HomeStack}
        options={{
          title: 'Home',
          tabBarLabel: 'Home',
          tabBarIcon: ({ color }) => <Text style={{ fontSize: 20, color }}>🏠</Text>,
        }}
      />
      <Tab.Screen
        name="MyPlants"
        component={PlantsStack}
        options={{
          title: 'Plants',
          tabBarLabel: 'Plants',
          tabBarIcon: ({ color }) => <Text style={{ fontSize: 20, color }}>🌿</Text>,
        }}
      />
      <Tab.Screen
        name="Tasks"
        component={TasksScreen}
        options={{
          title: 'Tasks',
          tabBarLabel: 'Tasks',
          tabBarIcon: ({ color }) => <Text style={{ fontSize: 20, color }}>✓</Text>,
        }}
      />
    </Tab.Navigator>
  );
};

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const { Text } = require('react-native');

  return (
    <SafeAreaProvider>
      <NavigationContainer>
        <Stack.Navigator screenOptions={{ headerShown: false }}>
          {!isLoggedIn ? (
            <Stack.Screen
              name="Welcome"
              options={{ animationEnabled: false }}
            >
              {(props) => (
                <WelcomeScreen
                  {...props}
                  onGetStarted={() => setIsLoggedIn(true)}
                  onLogin={() => setIsLoggedIn(true)}
                />
              )}
            </Stack.Screen>
          ) : (
            <Stack.Screen
              name="MainApp"
              component={AppTabs}
              options={{ animationEnabled: false }}
            />
          )}
        </Stack.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
}
