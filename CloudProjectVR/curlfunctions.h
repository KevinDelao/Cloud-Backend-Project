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

		std::string getNameHttpURL(){
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

		//Fills in First Name, Last Name, UserID, from JSON
		void getUserInfo(std::string rtext) {
			


		}


};

int getStatusCode(User myuser);