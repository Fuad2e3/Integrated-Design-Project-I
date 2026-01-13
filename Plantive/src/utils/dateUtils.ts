/**
 * Format a date for display
 * @param date Date to format
 * @returns Formatted date string (e.g., "Jan 15")
 */
export const formatDate = (date: Date): string => {
  return new Date(date).toLocaleDateString('en-US', {
    month: 'short',
    day: 'numeric',
  });
};

/**
 * Calculate days until a date
 * @param date Target date
 * @returns Number of days (negative if past)
 */
export const daysUntil = (date: Date): number => {
  const today = new Date();
  const target = new Date(date);
  const diff = target.getTime() - today.getTime();
  return Math.ceil(diff / (1000 * 60 * 60 * 24));
};

/**
 * Calculate next due date based on last date and frequency
 * @param lastDate Last occurrence date
 * @param frequencyDays Frequency in days
 * @returns Next due date
 */
export const calculateNextDueDate = (
  lastDate: Date,
  frequencyDays: number
): Date => {
  const next = new Date(lastDate);
  next.setDate(next.getDate() + frequencyDays);
  return next;
};

/**
 * Check if a date is today
 * @param date Date to check
 * @returns True if date is today
 */
export const isToday = (date: Date): boolean => {
  const today = new Date();
  const checkDate = new Date(date);
  return (
    today.getFullYear() === checkDate.getFullYear() &&
    today.getMonth() === checkDate.getMonth() &&
    today.getDate() === checkDate.getDate()
  );
};

/**
 * Get greeting based on time of day
 * @returns Greeting string
 */
export const getTimeBasedGreeting = (): string => {
  const hour = new Date().getHours();
  if (hour < 12) return 'Good morning';
  if (hour < 18) return 'Good afternoon';
  return 'Good evening';
};
