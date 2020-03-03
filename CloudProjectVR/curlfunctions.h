#include <iostream>
#include <cpr/cpr.h>

using namespace std;

class User{

private:
	std::string userName;
	std::string userNameHttpURL;
	std::string firstName;
	std::string lastName;
	std::string userID;
	std::string userInfo;

public:
		User() {
		std::string userName = "";
		std::string userNameHttpURL = "";
		std::string firstName = "";
		std::string lastName = "";
		std:string userID = "";
		std::string userInfo = "";
	   }

		User(std::string userName1, std::string userNameHttpURL2) {
			userName = userName1;
			userNameHttpURL = userNameHttpURL2;
		}

		std::string getUserName() {
			return userName;
		}

		std::string getNameHttpURL() {
			return userNameHttpURL;
		}

		void setUserName(std::string newName) {
		userName = newName;
		}

		void setUserNameHttpURL(std::string newUserNameHttpURL) {
		userNameHttpURL = newUserNameHttpURL;
		}
		
		//Prompts User to enter their username; username and usernameHttpURL are stored
		void promptUsername(std::string httpURL){
			std::cout << "1-------------------------------------------------------" << std::endl;
			std::cout << "what is your username?" << std::endl;
			std::cin >> userName;
			std::cout << "userName: " << userName;
			userNameHttpURL = httpURL;
			userNameHttpURL.append(userName);
			std::cout << std::endl;
		}

		//Checks to see if User already exists.  If it exists, returns false.  If it is a new User, return true.  If neither (some other error, end program)
		bool newUser() {
			auto r = cpr::Get(cpr::Url{ userNameHttpURL }
			);
			if (r.status_code == 200) {
				return false;
			}

			else if (r.status_code == 404) {
				return true;
			}

			else
				//end program but will keep true for now
				return true;

		}

		//Prompts user for more information and pushes information to cloud
		void createUser( std::string createUserHttpURL ) {
			std::cout << "username doesnt exist, using input to create a username." << std::endl;
			std::cout << "creating a user with username: " << userName << std::endl;
			std::cout << "what is your first name?" << std::endl;
			std::cin >> firstName;
			std::cout << "what is your last name?" << std::endl;
			std::cin >> lastName;
			//creating string variable to be inputted into body
			std::string userNameTemp("\"username\":");
			std::string firstNameTemp("\"firstName\":");
			std::string lastNameTemp("\"lastName\":");
			std::string firstNameQ = "\"" + firstName + "\"";
			std::string lastNameQ = "\"" + lastName + "\"";
			std::string userNameQ = "\"" + userName + "\"";
			std::string userBody;

			userBody = "{" + userNameTemp + userNameQ + ", " + firstNameTemp + firstNameQ + ", " + lastNameTemp + lastNameQ + "}";
			std::cout << userBody << std::endl;

			std::cout << std::endl << std::endl;
			auto r = cpr::Post(cpr::Url{ createUserHttpURL },
				//cpr::Body{ R"({"username": "user1" , "firstName":"john" , "lastName":"doe"})" },
				cpr::Body{ userBody },
				cpr::Header{ { "Content-Type", "application/json" } }
			);
		}


		//Fills in First Name, Last Name, UserID, from JSON
		void getUserInfo(std::string rtext) {
			


		}


};

int getStatusCode(User myuser);