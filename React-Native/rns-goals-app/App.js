import React, { useState } from "react";
import { StyleSheet, View, FlatList, Button, Text } from "react-native";
import Dialog, { DialogContent } from "react-native-popup-dialog";
import GoalInput from "./components/GoalInput";
import GoalItem from "./components/GoalItem";

export default function App() {
  const [userGoals, setUserGoals] = useState([]);
  const [isAddMode, setIsAddMode] = useState(false);
  const [deleteWindowVisible, setDeleteWindowVisible] = useState(false);
  const [goalToDelete, setGoalToDelete] = useState(0);

  const addGoalHandler = (goal) => {

      setUserGoals((currentGoals) => [
        ...currentGoals,
        { key: Math.random().toString(), title: goal.title, mGoal: goal.goal },
      ]);
      setIsAddMode(false);

  };

  const removeGoalHandlerDialog = (goalId) => {
    setDeleteWindowVisible(true);
    setGoalToDelete(goalId);
  };
  const removeGoalHandler = () => {
    setUserGoals((currentGoals) => {
      return currentGoals.filter((goal) => goal.key !== goalToDelete);
    });
    setGoalToDelete(0);
    setDeleteWindowVisible(false);
  };

  const cancelGoalAddition = () => {

    setIsAddMode(false);
  };

  const showOrHide = () => {
    if (deleteWindowVisible) {
      setDeleteWindowVisible(false);
    } else {
      setDeleteWindowVisible(true);
    }
  };

  const showModal = () => {
    setIsAddMode(true)
  }

  return (
    <View>
      <View>
        <Dialog visible={deleteWindowVisible}>
          <DialogContent style={styles.inputContainer} >
            <Text style={styles.input}> Are you Sure to Delete? </Text>
            <View style={styles.buttonContainer}>
              <View style={styles.button}>
                <Button color="red" title="NO" onPress={showOrHide}>
                  
                </Button>
              </View>
              <View style={styles.button}>
                <Button title="YES" onPress={removeGoalHandler}>
                  
                </Button>
              </View>
            </View>
          </DialogContent>
        </Dialog>
      </View>
      <View style={styles.screen}>
        <Button title="Add New Goal" onPress={showModal} />
        <GoalInput
          visible={isAddMode}
          onAddGoal={addGoalHandler}
          onCancel={cancelGoalAddition}
        />
        <FlatList
          data={userGoals}
          renderItem={(itemData) => (
            <GoalItem
              id={itemData.item.key}
              onDelete={removeGoalHandlerDialog}
              title= {itemData.item.title}
              goal={itemData.item.mGoal}
            />
          )}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  screen: {
    padding: 50,
  },
  text: {
    fontSize: 20
  },
  buttonContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    width: "55%",
  },
  button: {
    width: '40%',
    borderRadius: 10
  },
  input: {
    width: "80%",
    padding: 10,
    marginBottom: 10,
    textAlign: 'center'
  },
  inputContainer: {
    justifyContent: "center",
    alignItems: "center",
  },
});
