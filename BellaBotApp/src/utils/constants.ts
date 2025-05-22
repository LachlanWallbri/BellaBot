
import { ChartConfigType } from "./types";

// The number of data points that can be shown on the graph
// before going out of the graph's boundaries
export const MAX_DATA_POINTS = 30;

// To keep data within the bounds of the graph (`MAX_DATA_POINTS` data points),
// We average the first `NUM_VALUES_TO_AVERAGE` values every time
// The data list reaches `MAX_DATA_POINTS` in length
export const NUM_VALUES_TO_AVERAGE = 2;


export const DEFAULT_TEXT_SCREEN_TEXT = `Click to put whatever text you want here!

To begin, move the left stick forwards to move the bot.
Make sure your controller is paired, and that any wheel error is cleared by pressing "X".`;

export const BELLABOT_BLUE = "#2463eb";


// Previously, the team created a separate components/files for each
// of the line charts. This was a problem since the app would take time
// to fetch them and thus the frontend would show nothing for a couple seconds.
//
// The solution was to keep one line chart element but change the attributes
// depending on the chosen data to show, hence the different configs
export const BATTERY_CHART_CONFIG: ChartConfigType = {
  height: 500,
  maxValue: 105,
  minValue: 0,
  stepVal: 5,
  fontSize: 15,
  hideRules: true,
  showVerticalLines: true,
  yAxisLabels: Array.from({ length: 11 }, (_, i) => ((i * 10).toFixed(0)))
}

export const ANGULAR_SPEED_CHART_CONFIG: ChartConfigType = {
  height: 250,
  maxValue: 1.6,
  minValue: -1.6,
  fontSize: 20,
  stepVal: 0.2,
  hideRules: false,
  showVerticalLines: false,
  yAxisLabels: Array.from({ length: 21 }, (_, i) => (-1.6 + (i * 0.16)).toFixed(1))
}

export const LINEAR_SPEED_CHART_CONFIG: ChartConfigType = {
  height: 250,
  maxValue: 1,
  minValue: -1,
  fontSize: 20,
  hideRules: false,
  showVerticalLines: false,
  yAxisLabels: Array.from({ length: 21 }, (_, i) => ((i * 0.1) - 1).toFixed(1))
}

// How often we wish to get data from the CANBus
export const POLLING_RATE = 250;