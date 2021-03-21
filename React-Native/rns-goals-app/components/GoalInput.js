import React, { useState } from "react";
import { StyleSheet, View, TextInput, Button, Modal } from "react-native";

const GoalInput = (props) => {
  const [enteredGoal, setEnteredGoal] = useState({title: '', goal: ''});

  const goalInputHandler = (e) => {
    setEnteredGoal({
      ...enteredGoal,
        goal: e
    });
  };

  const goalTitleHandler = (e) => {
    setEnteredGoal({
      ...enteredGoal,
      title: e
    });
  }

  const addGoalHandler = (event) => {
    props.onAddGoal(enteredGoal);
    setEnteredGoal({title: '', goal: ''});
  };

  const goToBlank = (props) => {
    setEnteredGoal({title: '', goal: ''});
    props.onCancel()
  }

  return (
    <Modal visible={props.visible} animationType="slide">
      <View style={styles.inputContainer}>
      <TextInput
          placeholder="Title"
          style={styles.input}
          onChangeText={goalTitleHandler}
          value={enteredGoal.title}
        />
        <TextInput
          placeholder="Your Goal"
          style={styles.input}
          onChangeText={goalInputHandler}
          value={enteredGoal.goal}
        />
        <View style={styles.buttonContainer}>
          <View style={styles.button}>
            <Button title="CANCEL" color="red" onPress={() => goToBlank(props)} />
          </View>
          <View style={styles.button}>
            <Button disabled= {enteredGoal.title == 0 || enteredGoal.goal.size == 0} id= "buttonAdd" title="ADD" onPress={addGoalHandler} />
          </View>
        </View>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  screen: {
    padding: 50,
  },
  inputContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  input: {
    width: "80%",
    borderColor: "black",
    borderWidth: 2,
    padding: 10,
    marginBottom: 10,
  },
  buttonContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    width: "60%",
  },
  button: {
    width: '40%'
  },
});

export default GoalInput;
