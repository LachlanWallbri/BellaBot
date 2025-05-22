import { MAX_DATA_POINTS } from "./constants";
import { graphDataType } from "./types";

// Stats come in continually and holding that information in one long array
// crashes the android device, so we average the first `n` values once the
// inputted array of data points is larger than `MAX_DATA_POINTS` in length.
export const averageFirstNValues = (list: graphDataType[], n: number): graphDataType[] => {
  if (list.length < MAX_DATA_POINTS) {
    return list;
  }

  let sum = 0;

  for (let i = 0; i < n; i++) {
    sum += list[i].value;
  }

  const average = parseInt((sum / n).toFixed(0));

  const averagedDataPoint: graphDataType = {
    value: average,
    dataPointText: String(average)
  };

  const result: graphDataType[] = [averagedDataPoint, ...list.slice(n, MAX_DATA_POINTS)];

  return result;
}
