package repositories;

import models.User;
import states.*;
import utils.Constants;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepository {

    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        if (users.containsKey(user.getUsername())) {
            ErrorMessageState errorMessageState = new ErrorMessageState(
                    String.format(Constants.ALREADY_EXISTING_USER_MESSAGE, user.getUsername()),
                    new RegistrationFormState());

            StateManager.setCurrentState(errorMessageState);
        } else if (user.getFirstName().length() == 0
                || user.getLastName().length() == 0
                || user.getPassword().length() == 0
                || user.getUsername().length() == 0) {
            ErrorMessageState errorMessageState = new ErrorMessageState(
                    Constants.FILL_ALL_EMPTY_FIELDS_MESSAGE,
                    new RegistrationFormState());

            StateManager.setCurrentState(errorMessageState);
        } else {
            users.put(user.getUsername(), user);
            save(user);
            SuccessMessageState successMessageState = new SuccessMessageState(
                    Constants.SUCCESSFUL_REGISTER_MESSAGE,
                    new MainMenuState());

            StateManager.setCurrentState(successMessageState);
        }
    }


   
    public void updateUser(User user) {
        String editedData = String.format("%s %s %s %s",
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword());

        this.users.put(user.getUsername(), user);

        FileOutputStream writer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.USERS_FILE_PATH))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineTokens = line.split("\\s+");
                if (lineTokens[0].equals(user.getUsername())) {
                    text.append(editedData);
                } else {
                    text.append(line);
                }
                text.append("\n");
            }

            writer = new FileOutputStream(Constants.USERS_FILE_PATH);
            writer.write(text.toString().getBytes());
        } catch (FileNotFoundException e) {
            System.out.println(Constants.FILE_FOUNDING_UNSUCCESSFUL_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(Constants.FILE_WRITING_FAILURE_MESSAGE);
            e.printStackTrace();
        } finally {
        	try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

        SuccessMessageState successMessageState = new SuccessMessageState(
                Constants.PROFILE_SUCCESSFUL_EDITING_MESSAGE,
                new MainMenuState());

        StateManager.setCurrentState(successMessageState);
    }

    public User findUserByUsername(String username) {
        User user = null;

        if (!users.containsKey(username)) {
            ErrorMessageState errorMessageState = new ErrorMessageState(
                    String.format(Constants.USER_DOES_NOT_EXIST_MESSAGE, username),
                    new LoginFormState());

            StateManager.setCurrentState(errorMessageState);
        } else {
            user = users.get(username);
        }

        return user;
    }

    public Iterable<User> getAllUsers() {
        List<User> allUsers = users.values().stream().collect(Collectors.toList());
        return allUsers;
    }

    // load all users from .txt file
    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.USERS_FILE_PATH))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] token = line.split(" ");
                String username = token[0];
                String firstName = token[1];
                String lastName = token[2];
                String password = token[3];

                if (!users.containsKey(username)) {
                    User user = new User(username, firstName, lastName, password);
                    users.put(username, user);
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException exception) {
            System.err.println(Constants.FILE_READING_FAILURE_MESSAGE);
            exception.printStackTrace();
        }
    }

    // Save registered user into a .txt file
    private void save(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Constants.USERS_FILE_PATH, true), true)) {
            writer.println(user.getUsername() + " "
                    + user.getFirstName() + " "
                    + user.getLastName() + " "
                    + user.getPassword());

        } catch (IOException exception) {
            System.err.println(Constants.FILE_WRITING_FAILURE_MESSAGE);
            exception.printStackTrace();
        }
    }

}
