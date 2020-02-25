#include <iostream>

using namespace std;

class User{
private:
	std::string userName;
	std::string userNameHttpURL;
	std::string firstName;
	std::string lastName;
public:
		User() {
		std::string userName = "";
		std::string userNameHttpURL = "";
		std::string firstName = "";
		std::string lastName = "";
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

		void getUserInformation(std::string httpURL){
			std::cout << "1-------------------------------------------------------" << std::endl;
			std::cout << "what is your username?" << std::endl;
			std::cin >> userName;
			std::cout << "userName: " << userName;
			userNameHttpURL = httpURL;
			userNameHttpURL.append(userName);
			std::cout << std::endl;
		}



};