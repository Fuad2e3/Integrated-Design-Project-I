# Plantive - AI Agent Instructions

## Project Overview

**Plantive** is a React Native + Expo mobile app that makes plant care simple and stress-free for beginners and urban gardeners. The design philosophy prioritizes **zero thinking** - users should understand what to do instantly through visual cues, minimal text, and one action per screen.

## Architecture

### Tech Stack
- **Framework**: React Native (Expo)
- **Language**: TypeScript
- **State Management**: Zustand (lightweight, minimal boilerplate)
- **Navigation**: React Navigation (bottom tabs + stack navigator)
- **Styling**: React Native StyleSheet (no CSS framework)

### Key Directory Structure
```
src/
  screens/         # 5 main screens: Welcome, Dashboard, Plants, Details, Tasks
  components/      # Reusable UI: Button, Card, Badge, HealthBadge
  store/          # Zustand store (plantStore.ts) - single source of truth
  theme/          # Design tokens (colors, typography, spacing)
  App.tsx         # Navigation root with tab and stack navigators
```

## Core Data Model

### Plant Interface
```typescript
interface Plant {
  id: string;
  name: string;                  // e.g., "Monstera Deliciosa"
  scientificName: string;        // e.g., "Monstera deliciosa"
  image?: string;                // URI to plant image (placeholder: emoji)
  location: 'indoor' | 'outdoor';
  healthStatus: 'healthy' | 'warning' | 'urgent';  // Color-coded
  lastWatered: Date;             // ISO string or Date object
  wateringFrequencyDays: number; // e.g., 7
  lastFertilized: Date;
  fertilizeFrequencyDays: number; // e.g., 30
  notes?: string;
}
```

### Task Interface
```typescript
interface Task {
  id: string;
  plantId: string;               // References Plant.id
  type: 'water' | 'fertilize' | 'other';
  dueDate: Date;
  completed: boolean;
  scheduledDate?: Date;          // User-rescheduled date
}
```

## Design System (Critical for All UI Work)

### Color Constants (in `src/theme/tokens.ts`)
- `Colors.primary` (#2D5016) - Deep green - primary actions, headers
- `Colors.secondary` (#7CB342) - Light green - secondary actions
- `Colors.healthy` (#4CAF50) - Green indicator
- `Colors.warning` (#FFC107) - Yellow indicator (needs care)
- `Colors.urgent` (#F44336) - Red indicator (urgent attention)

### Key Pattern: Health Status Indicator
Every plant display uses color coding:
- Green dot = Healthy
- Yellow dot = Warning
- Red dot = Urgent

This communicates status **visually in 0.5 seconds** without reading text.

### Spacing (always use `Spacing` tokens)
- `Spacing.xs` (4), `Spacing.sm` (8), `Spacing.md` (16), `Spacing.lg` (24)
- Consistent padding/margins across all screens

### Example Component Usage
```typescript
import { Colors, Spacing, Typography } from '@theme/tokens';

<View style={{ padding: Spacing.md, backgroundColor: Colors.surface }}>
  <Text style={{ color: Colors.textPrimary, fontSize: Typography.sizes.lg }}>
    Text
  </Text>
</View>
```

## Screen Responsibilities

### WelcomeScreen
- Entry point before login
- 3 swipe cards showing benefits (Add plant, Get reminders, Ask AI)
- Two buttons: "Get Started" and "Login"
- **Key UX**: Tagline: "Plant care made simple, calm, and mistake-free"

### HomeDashboardScreen  
- **Most important screen** - instant status overview
- Greeting (time-based: "Good morning", "Good afternoon", etc.)
- Weather info placeholder (currently static)
- 3 summary cards: Healthy count, Needs care count, Urgent count
- Today's tasks list (use `getTodaysTasks()` from store)
- Quick preview of first 2 plants
- Navigation buttons to detailed sections

### MyPlantsScreen
- Grid layout (2 columns) of plant cards
- Filters: All, Indoor, Outdoor, Healthy, Needs care
- Add plant floating button (+)
- Tap anywhere on card → PlantDetailsScreen

### PlantDetailsScreen
- Large plant image area (emoji placeholder)
- Plant name + scientific name
- Health badge + quick action buttons (Water, Fertilize)
- Care info section: watering frequency, fertilize frequency, location
- Care timeline: last watered date, last fertilized date
- Optional notes field
- Back button

### TasksScreen
- Two tabs: Today / Upcoming
- Checkbox-style task items (◯ symbol)
- Tap to complete (future: green animation)
- Shows plantId reference so user sees "Water Monstera" (not just "Water 1234")

## State Management Pattern

### Store Location: `src/store/plantStore.ts`
Uses Zustand for simplicity:

```typescript
import { usePlantStore } from '@store/plantStore';

// In components
const plants = usePlantStore((state) => state.plants);
const addPlant = usePlantStore((state) => state.addPlant);
const todaysTasks = usePlantStore((state) => state.getTodaysTasks());
```

### Key Store Methods
- `addPlant(plant)` - Add new plant (auto-generates ID)
- `updatePlant(id, updates)` - Partial update
- `removePlant(id)` - Delete plant and associated tasks
- `addTask(task)` - Create task for plant
- `completeTask(id)` - Mark task done
- `getTodaysTasks()` - Queries tasks due today
- `getUpcomingTasks()` - Queries future tasks

## Component Library

### Button
```typescript
<Button
  label="Water"
  onPress={() => {}}
  variant="primary" | "secondary" | "ghost"
  size="sm" | "md" | "lg"
  fullWidth={true}
  disabled={false}
/>
```

### Card
```typescript
<Card variant="default" | "elevated">
  {children}
</Card>
```

### PlantCard
Visual plant summary - used in grids:
```typescript
<PlantCard
  name="Monstera"
  health="healthy" | "warning" | "urgent"
  needsWater={true}
  onPress={() => {}}
/>
```

### HealthBadge
Status indicator - used everywhere:
```typescript
<HealthBadge status="healthy" label="All good" />
```

## Development Workflows

### Adding a New Screen
1. Create file in `src/screens/NewScreen.tsx`
2. Define interface props with navigation callbacks (onNavigateTo*, onGoBack)
3. Use `usePlantStore` hook for data
4. Apply theme tokens for styling
5. Export in `src/screens/index.ts`
6. Add to navigation in `src/App.tsx`

### Adding a New Component
1. Create file in `src/components/NewComponent.tsx`
2. Use theme tokens and consistent spacing
3. Export in `src/components/index.ts`
4. Example from codebase: `Card.tsx` shows proper pattern

### Modifying Store
- Only modify `src/store/plantStore.ts`
- Keep getter/setter patterns consistent
- Use auto-generated IDs (Date.now().toString())
- Filter out deleted items (don't just mark as deleted)

### Styling Rules
- **NEVER** use hardcoded colors/spacing - always use theme tokens
- All shadows from `Shadows.sm`, `Shadows.md`, `Shadows.lg`
- All margins/padding from `Spacing` enum
- Border radius from `BorderRadius` enum
- Font sizes from `Typography.sizes`

## Important Patterns to Follow

### "Zero Thinking" UX
- Each screen answers ONE question only
- Use color/emoji for instant status (not text)
- Primary action is always obvious
- No long paragraphs - use bullets/icons where possible

### Date Handling
- Store dates as Date objects (JS native)
- Convert to string only for JSON storage
- Example: `new Date(plant.lastWatered).toLocaleDateString()`

### Plant Health Calculation
Health is currently set manually when creating/updating plants. Future enhancement: AI analysis from photos.

### Emoji Icons
- Water: 💧
- Fertilize: 🌱
- Plant: 🌿
- Tasks: ✓
- Home: 🏠

These are used in bottom tab navigation and task icons.

## Common Tasks

### Creating a Plant
```typescript
usePlantStore.getState().addPlant({
  name: 'Monstera',
  scientificName: 'Monstera deliciosa',
  location: 'indoor',
  healthStatus: 'healthy',
  lastWatered: new Date(),
  wateringFrequencyDays: 7,
  fertilizeFrequencyDays: 30,
  lastFertilized: new Date(),
});
```

### Getting Plant Details with Tasks
```typescript
const plant = usePlantStore.getState().getPlantById(plantId);
const relatedTasks = usePlantStore.getState().tasks.filter(t => t.plantId === plantId);
```

### Filtering Plants
```typescript
const indoorPlants = plants.filter(p => p.location === 'indoor');
const urgentPlants = plants.filter(p => p.healthStatus === 'urgent');
```

## Testing

- TypeScript ensures compile-time type safety
- No comprehensive test suite yet (future enhancement)
- Manual testing with Expo Go or emulator recommended

## Performance Considerations

- Zustand is very lightweight (no Redux complexity)
- Avoid large arrays of tasks - pagination not yet implemented
- React Navigation is optimized for mobile animations
- No external image loading yet - emojis for now

## Future Extensions (documented but not yet built)

- Push notifications for task reminders
- Plant image capture from camera/gallery
- Weather API integration
- Fertilizer/soil type recommendations
- Photo timeline of growth
- Social sharing
- Dark mode toggle
- Backup/sync across devices

## Debugging Tips

1. **State not updating?** - Check that you're using the Zustand selector correctly
2. **Navigation not working?** - Verify screen is exported and added to navigation stack
3. **Styling off?** - Use theme tokens, not hardcoded values
4. **Plant data missing?** - Ensure plant.id is being generated and used as key

---

**Key Principle**: Keep everything **visual, simple, and calm**. When in doubt, remove text and add emoji/color.
