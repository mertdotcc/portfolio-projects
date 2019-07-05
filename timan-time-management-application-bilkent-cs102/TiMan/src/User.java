import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User {
	
	public String name;
	public String username;
	public String password;
	public String favoriteAnimal;
	public Gender gender;
	public int age;
	public int height;
	public double weight;
	public ArrayList<DailyActivityList> dailyActivities;
	//public FileInteractor fileInteractor;
	
	public enum Gender {
		MALE, FEMALE, OTHER
	}
	
	//Constructor
	public User (String name, String username, String password , String favoriteAnimal, Gender gender, int age, int height, int weight) /*throws IOException*/{
		this.name = name;
		setUsername(username);
		this.password = password;
		this.favoriteAnimal = favoriteAnimal;
		this.gender = gender;
		this.age = age;
		setHeight(height);
		setWeight(weight);
		dailyActivities = new ArrayList<DailyActivityList>();
		//fileInteractor = new FileInteractor(name);
	}
	
	//Getter-Setter
    public String getName(){
		return name;
    }
    public void setName(String name) {
    		this.name = name;
    }
    public String getUsername(){
		return username;
    }
    public void setUsername(String username) {
    		//if(usernameTaken(username, userList)) {
        		this.username = username;
    		//}
    }
    public String getPassword(){
		return password;
    }
    public void setPassword(String password) {
    		this.password = password;
    }
    public String getFavoriteAnimal(){
		return favoriteAnimal;
    }
    public void setFavoriteAnimal(String favoriteAnimal) {
    		this.favoriteAnimal = favoriteAnimal;
    }
    public Gender getGender() {
    		return gender;
    }
    public void setGender(Gender gender) {
    		this.gender = gender;
    }
    public int getAge(){
		return age;
    }
    public void setAge(int age) {
    		if(age >= 0) {
    			this.age = age;
    		}
    }
    public int getHeight(){
		return height;
    }
    public void setHeight(int height) {
		if(height >= 0) {
			this.height = height;
		}
    }
    public double getWeight(){
		return weight;
    }
    public void setWeight(double weight) {
		if(weight >= 0) {
			this.weight = weight;
		}
    }
    public ArrayList<DailyActivityList> getActivities(){
    		return dailyActivities;
    }
    /*public void setActivitiesByDate(Date date,ArrayList<Activity> activities) {
    		dailyActivities.add(date, activities ) ;
    }*/
    
    //Methods
    
    /**
     * @param userList
     * @param username 
     * @return false if username is already taken.
     */
    
    public boolean usernameTaken(String username, ArrayList<User> userList) {
    	boolean usernameTaken = false;
		for(User u : userList) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				usernameTaken = true;
			}
		}
		return usernameTaken;
    }



    public void addActivityToDailyList( Activity a, Date date) {
    	for( int i = 0; i < dailyActivities.size(); i++) {
    		if( date.equals( dailyActivities.get(i).getDate())) {
    			dailyActivities.get(i).getActivities().add( a);
			}
		}
	}

	public DailyActivityList getActivitiesByDate( Date date) {
    	for( int i = 0; i < dailyActivities.size(); i++) {
    		if( date.equals( dailyActivities.get(i).getDate())) {
    			return dailyActivities.get(i);
			}
		}
		return null;

	}

	/*public ArrayList<Integer> getActivityDurationsByDate( Date date) {
    	Scanner fileScan = fileInteractor.getFileScanner();
    	int duration = 0;
    	while( fileScan.hasNextLine()) {
    		if( fileScan.next().equals(date)) {
    			while(fileScan.hasNext()) {

				}
			}
		}
	}*/

	public void printInfo() throws IOException {
		FileInteractor fileInteractor = new FileInteractor(username+".txt");
		fileInteractor.getFileWriter().println(getName()+"-"+getUsername()+"-"+getPassword()+"-"+getFavoriteAnimal()+"-"+getGender() +"-"+getAge()+"-"+getHeight()+"-"+getWeight());
		fileInteractor.getFileWriter().close();
	}
	public void printInfo(String filename) throws IOException {
		FileInteractor fileInteractor = new FileInteractor(filename);
		fileInteractor.getFileWriter().print(getUsername()+"-");
		fileInteractor.getFileWriter().close();
	}

    
}
