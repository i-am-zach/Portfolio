import React, { useState } from "react";
import { View, Dimensions } from "react-native";
import PropTypes from "prop-types";
import Svg, { Circle, Text } from "react-native-svg";

const size = Dimensions.get("screen").width - 32;
const strokeWidth = Math.floor((size / 2) * 0.25);
const radius = Math.floor(size / 2 - strokeWidth);
const circumfrence = 2 * radius * Math.PI;
const color = "#FF6969";

const CircularProgress = props => {
  const { progress, duration } = props;

  const percentFilled = (progress, duration) => {
    return progress > 0 ? (duration - progress) / duration : 0;
  };

  const formatTimer = num => {
    return num > 0 ? num.toFixed(2) : '0.00';
  }

  const strokeDashoffset = percentFilled(progress, duration) * 2 * Math.PI * radius;

  return (
    <View>
      <Svg width={size} height={size}>
        <Circle
          cx={size / 2}
          cy={size / 2}
          r={radius}
          fill="none"
          stroke={color}
          strokeWidth={strokeWidth}
          strokeDasharray={`${circumfrence} ${circumfrence}`}
          strokeDashoffset={strokeDashoffset}
        />
        <Text
          fill={color}
          stroke="none"
          fontSize="72"
          fontWeight="bold"
          x={size / 2}
          y={size * 5 / 9}
          textAnchor="middle"
        >
          {formatTimer(progress)}
        </Text>
      </Svg>
    </View>
  );
};

CircularProgress.propTypes = {
  duration: PropTypes.number,
  progress: PropTypes.number
};

export default CircularProgress;
