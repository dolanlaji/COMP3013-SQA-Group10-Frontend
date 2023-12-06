package com.example.group10_sqa_mentalhealthapp;
/**
 * Represents a goal card with a title and completion status.
 *
 * <p>A GoalCard object contains information about a specific goal, including its title and whether it has been completed.
 */
public class GoalCard {
    // Title of the goal
    public String title;
    // Completion status of the goal
    public boolean done;
    /**
     * Default constructor for GoalCard.
     *
     * <p>Initializes a new GoalCard with the completion status set to false.
     */
    public GoalCard() {
        done = false;
    }
}
