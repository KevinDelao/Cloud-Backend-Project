#include <iostream>

using namespace std;

class User{
private:
	std::string userName;
	std::string userNameHttpURL;
public:
		User() {
		std::string userName = "";
		std::string userNameHttpURL = "";
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


};