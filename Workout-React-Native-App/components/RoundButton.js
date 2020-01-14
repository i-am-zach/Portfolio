import React from "react";
import { StyleSheet, View, Text, TouchableOpacity } from "react-native";
import PropTypes from "prop-types";

const RoundButton = ({ title, onPress}) => {
  return (
    <TouchableOpacity style={styles.button} onPress={onPress} activeOpacity={0.8}>
      <View style={styles.innerBoarder}>
        <Text style={styles.buttonText}>{title}</Text>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    width: 80,
    height: 80,
    borderRadius: 40,
    backgroundColor: "#ff6969",
    alignItems: "center",
    justifyContent: "center"
  },
  buttonText: {
      color: 'white',
  },
  innerBoarder: {
      height: 76,
      width: 76,
      borderRadius: 38,
      borderColor: '#FFf9e8',
      borderWidth: 2,
      backgroundColor: 'transparent',
      alignItems: 'center',
      justifyContent: 'center',
  }
});

export default RoundButton;
