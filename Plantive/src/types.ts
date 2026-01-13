// Global type definitions for Plantive

declare global {
  namespace NodeJS {
    interface ProcessEnv {
      EXPO_PUBLIC_API_URL?: string;
    }
  }
}

export {};
