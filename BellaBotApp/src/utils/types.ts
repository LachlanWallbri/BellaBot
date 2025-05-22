// Types to be used throughout the project

export type graphDataType = {
  value: number,
  dataPointText: string
}

export type screenType = "LOGIN" | "LOGOUT" | "FACE" | "STATS" | "GRAPHS" | "TEXT"
export type buttonLabelType = "Battery Percent" | "Linear Speed" | "Angular Speed"
export type graphDataUnit = "(%)" | "(rad/s)" | "(m/s)"

export type gyroDataType = {
  x: number,
  y: number,
  z: number,
}

export interface DataContextType {
  acceleration: number;
  deceleration: number;
  updateAcceleration: (newAcceleration: number) => Promise<void>;
  updateDeceleration: (newDeceleration: number) => Promise<void>;
  lastBatteryPercent: number;
  lastLinearSpeed: number;
  lastAngularSpeed: number;
  instantGyro: gyroDataType;
  accumulativeGyro: gyroDataType;
  leftWheelError: string;
  rightWheelError: string;
  isConnected: boolean;
  chargeState: string;
}

export interface ChartConfigType {
  height: number;
  maxValue: number;
  minValue: number;
  fontSize: number,
  hideRules: boolean;
  stepVal?: number;
  showVerticalLines: boolean;
  yAxisLabels: string[]
}