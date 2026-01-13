import { create } from 'zustand';

export interface Plant {
  id: string;
  name: string;
  scientificName: string;
  image?: string;
  location: 'indoor' | 'outdoor';
  healthStatus: 'healthy' | 'warning' | 'urgent';
  lastWatered: Date;
  wateringFrequencyDays: number;
  fertilizeFrequencyDays: number;
  lastFertilized: Date;
  notes?: string;
}

export interface Task {
  id: string;
  plantId: string;
  type: 'water' | 'fertilize' | 'other';
  dueDate: Date;
  completed: boolean;
  scheduledDate?: Date;
}

interface PlantStore {
  plants: Plant[];
  tasks: Task[];
  
  // Plant actions
  addPlant: (plant: Omit<Plant, 'id'>) => void;
  updatePlant: (id: string, updates: Partial<Plant>) => void;
  removePlant: (id: string) => void;
  
  // Task actions
  addTask: (task: Omit<Task, 'id'>) => void;
  completeTask: (id: string) => void;
  rescheduleTask: (id: string, newDate: Date) => void;
  
  // Queries
  getTodaysTasks: () => Task[];
  getUpcomingTasks: () => Task[];
  getPlantById: (id: string) => Plant | undefined;
}

export const usePlantStore = create<PlantStore>((set, get) => ({
  plants: [],
  tasks: [],
  
  addPlant: (plant) => {
    const id = Date.now().toString();
    set((state) => ({
      plants: [...state.plants, { ...plant, id }],
    }));
  },
  
  updatePlant: (id, updates) => {
    set((state) => ({
      plants: state.plants.map((p) =>
        p.id === id ? { ...p, ...updates } : p
      ),
    }));
  },
  
  removePlant: (id) => {
    set((state) => ({
      plants: state.plants.filter((p) => p.id !== id),
      tasks: state.tasks.filter((t) => t.plantId !== id),
    }));
  },
  
  addTask: (task) => {
    const id = Date.now().toString();
    set((state) => ({
      tasks: [...state.tasks, { ...task, id, completed: false }],
    }));
  },
  
  completeTask: (id) => {
    set((state) => ({
      tasks: state.tasks.map((t) =>
        t.id === id ? { ...t, completed: true } : t
      ),
    }));
  },
  
  rescheduleTask: (id, newDate) => {
    set((state) => ({
      tasks: state.tasks.map((t) =>
        t.id === id ? { ...t, scheduledDate: newDate } : t
      ),
    }));
  },
  
  getTodaysTasks: () => {
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    return get().tasks.filter((t) => {
      const taskDate = new Date(t.scheduledDate || t.dueDate);
      taskDate.setHours(0, 0, 0, 0);
      return !t.completed && taskDate.getTime() === today.getTime();
    });
  },
  
  getUpcomingTasks: () => {
    const today = new Date();
    return get().tasks.filter((t) => {
      const taskDate = new Date(t.scheduledDate || t.dueDate);
      return !t.completed && taskDate > today;
    });
  },
  
  getPlantById: (id) => {
    return get().plants.find((p) => p.id === id);
  },
}));
