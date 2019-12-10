#include <cpr/cpr.h>
//#include "api.h"
//#include "auth.h"
//#include "cprtypes.h"
//#include "response.h"
//#include "session.h"
//#include "status_codes.h"
//
//#include "body.h"
//#include "connect_timeout.h"
//#include "cookies.h"
//#include "defines.h"
//#include "digest.h"
//#include "error.h"
//#include "low_speed.h"
//#include "max_redirects.h"
//#include "multipart.h"
//#include "parameters.h"
//#include "payload.h"
//#include "proxies.h"
//#include "ssl_options.h"
//#include "timeout.h"
//#include "user_agent.h"
//#include "util.h"
//#include "verbose.h"

//curl libraries that probably arent needed based on cpr
//#include "curl.h"
//#include "curlholder.h"
//#include "curlver.h"
//#include "mprintf.h"
//#include "multi.h"
//#include "easy.h"
//#include "typecheck-gcc.h"
//#include "urlapi.h"
//#include "stdcheaders.h"
//#include "system.h"
//#include "stdcheaders.h"

#include <iostream>
#include <string>
#include <typeinfo>
#include <cstring>

int main()
{

	std::string userName;
	std::string httpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user?username=");
	std::string userNameHttpURL = httpURL;
	int statusCode = 0;
	//1*******
	std::cout << "what is your username?" << std::endl;
	std::cin >> userName;
	std::cout << "userName: " << userName;
	userNameHttpURL.append(userName);
	std::cout << std::endl;

	{
		//std::cout << "Returning the user info" << std::endl;
		auto r = cpr::Get(cpr::Url{ userNameHttpURL }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl << std::endl;
		statusCode = r.status_code;
		if (statusCode == 200) {
			std::cout << "username exists!" << std::endl;

			//code: grabs UUID - need to change lngth of idname 
			std::string userInfo = r.text;
			std::cout << "TEST:" + userInfo << std::endl;
			//std::cout << typeid(userName).name() << std::endl; //find variable type
			std::string userIdTxt = "userId";
			int userIdStore = 0;
			userIdStore = userInfo.find(userIdTxt);
			int lenn = userInfo.length();
			std::cout << lenn << std::endl;
			std::cout << userIdStore << std::endl;
			char userInfoArr[37] = "";
			int j = 0;
			int userIdNameLen = 9;
			int UUIDLen = 37;
			int userIdNameLenMin1 = 8;
			for (int i = userIdStore + userIdNameLen; i < userIdStore + UUIDLen + userIdNameLenMin1; i++) {
				
				userInfoArr[j] = userInfo[i];
				j++;
				std::cout << userInfoArr[j];
			}
			j = 0;
			std::cout << std::endl;
			std::string userIdStr(userInfoArr);
			std::cout << "userId: ";
			std::cout << userIdStr << std::endl;
			//end of UUID
		}

	}

	std::cout << std::endl;

	if(statusCode == 404){
		std::string createUserHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user");
		std::cout << "TEST: URL - " + createUserHttpURL << std::endl;
		std::cout << "username doesnt exist, using input to create a username." << std::endl;
		std::cout << "creating a user with username: " << userName << std::endl;
		std::cout << "what is your first name?" << std::endl;
		std::string firstName;
		std::cin >> firstName;
		std::cout << "what is your last name?" << std::endl;
		std::string lastName;
		std::cin >> lastName;
		//creating string variable to be inputted into body
		std::string userNameTemp("\"username\":");
		std::string firstNameTemp("\"firstName\":");
		std::string lastNameTemp("\"lastName\":");
		std::string firstNameQ = "\"" + firstName + "\"";
		std::string lastNameQ = "\"" + lastName + "\"";
		std::string userNameQ = "\"" + userName + "\"";
		std::string userBody;

		userBody = "{" + userNameTemp + userNameQ + ", " + firstNameTemp + firstNameQ + ", "+ lastNameTemp + lastNameQ + "}";
		std::cout << userBody << std::endl;

		std::cout << std::endl << std::endl;
		auto r = cpr::Post(cpr::Url{ createUserHttpURL },
			//cpr::Body{ R"({"username": "user1" , "firstName":"john" , "lastName":"doe"})" },
			cpr::Body{userBody},
			cpr::Header{ { "Content-Type", "application/json" } }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl;
		
		//code: grabs UUID - need to change lngth of idname 
		std::string userInfo = r.text;
		std::cout << "TEST:" + userInfo << std::endl;
		//std::cout << typeid(userName).name() << std::endl; //find variable type
		std::string userIdTxt = "userId";
		int userIdStore = 0;
		userIdStore = userInfo.find(userIdTxt);
		int lenn = userInfo.length();
		std::cout << lenn << std::endl;
		std::cout << userIdStore << std::endl;
		char userInfoArr[37] = "";
		int j = 0;
		int userIdNameLen = 9;
		int UUIDLen = 37;
		int userIdNameLenMin1 = 8;
		for (int i = userIdStore + userIdNameLen; i < userIdStore + UUIDLen + userIdNameLenMin1; i++) {

			userInfoArr[j] = userInfo[i];
			j++;
			std::cout << userInfoArr[j];
		}
		j = 0;
		std::cout << std::endl;
		std::string userIdStr(userInfoArr);
		std::cout << "userId: ";
		std::cout << userIdStr << std::endl;
		//end of UUID
	
		
	}

	//2*******
	std::string deviceHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/device?name=");

	std::cout << "what is your device? (ex: james_device)" << std::endl;
	std::string deviceName;
	std::cin >> deviceName;
	std::cout << "device name is: " << deviceName;
	std::cout << std::endl;
	deviceHttpURL.append(deviceName);

	//int statusCode = 0;
	{
		auto r = cpr::Get(cpr::Url{ deviceHttpURL }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl << std::endl;
		//statusCode = r.status_code;
		statusCode = r.status_code;
		if (statusCode == 200) {
			std::cout << "device exists!" << std::endl << std::endl;
			//code: grabs UUID - need to change lngth of idname 
			std::string userInfo = r.text;
			std::cout << "TEST:" + userInfo << std::endl;
			//std::cout << typeid(userName).name() << std::endl; //find variable type
			std::string userIdTxt = "deviceId";
			int userIdStore = 0;
			userIdStore = userInfo.find(userIdTxt);
			int lenn = userInfo.length();
			std::cout << lenn << std::endl;
			std::cout << userIdStore << std::endl;
			char userInfoArr[37] = "";
			int j = 0;
			int userIdNameLen = 11;
			int UUIDLen = 37;
			int userIdNameLenMin1 = 10;
			for (int i = userIdStore + userIdNameLen; i < userIdStore + UUIDLen + userIdNameLenMin1; i++) {

				userInfoArr[j] = userInfo[i];
				j++;
				std::cout << userInfoArr[j];
			}
			j = 0;
			std::cout << std::endl;
			std::string deviceIdStr(userInfoArr);
			std::cout << "deviceId: ";
			std::cout << deviceIdStr << std::endl;
			//end of UUID

		}
	}


	if (statusCode == 404) {
		
		std::cout << "Device doesnt exist, using input to create a device." << std::endl;
		std::cout << "creating a device with input: " << deviceName << std::endl << std::endl;
		std::cout << "what is your userId?" << std::endl;
		//std::cin >> userId;
		
		std::string deviceNameTemp("\"name\":");
		std::string deviceNameQ = "\"" + deviceName + "\"";
		std::string deviceUserIdTemp("\"userId\":");
		std::string deviceUserIdQ = "\"" + deviceName + "\"";
		std::string deviceBody = "{" + deviceNameTemp + deviceNameQ + "}";
		
		auto r = cpr::Post(cpr::Url{ deviceHttpURL },

			cpr::Body{ deviceBody },
			cpr::Header{ { "Content-Type", "application/json" } }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl << std::endl;

		//code: grabs UUID - need to change lngth of idname 
		std::string userInfo = r.text;
		std::cout << "TEST:" + userInfo << std::endl;
		//std::cout << typeid(userName).name() << std::endl; //find variable type
		std::string userIdTxt = "deviceId";
		int userIdStore = 0;
		userIdStore = userInfo.find(userIdTxt);
		int lenn = userInfo.length();
		std::cout << lenn << std::endl;
		std::cout << userIdStore << std::endl;
		char userInfoArr[37] = "";
		int j = 0;
		int userIdNameLen = 11;
		int UUIDLen = 37;
		int userIdNameLenMin1 = 10;
		for (int i = userIdStore + userIdNameLen; i < userIdStore + UUIDLen + userIdNameLenMin1; i++) {

			userInfoArr[j] = userInfo[i];
			j++;
			std::cout << userInfoArr[j];
		}
		j = 0;
		std::cout << std::endl;
		std::string deviceIdStr(userInfoArr);
		std::cout << "deviceId: ";
		std::cout << deviceIdStr << std::endl;
		//end of UUID
	}

	//3**********
	////checks if gamesession (faceroller) exists with given userId. Usually just means if Id exists, then game session exists
	//{
	//	std::cout << "checks if faceroller game session exists" << std::endl;
	//	auto r = cpr::Get(cpr::Url{ "http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user/fa305be6-c014-4744-bc1b-3ad539508b07/game-session" }
	//	);
	//	std::cout << "Returned Status:" << r.status_code << std::endl;
	//	std::cout << "Returned Text:" << r.text << std::endl;

	//}

	//using game session Id to create game session. When updating, just use the same userId and input whatever info
	//POST info 
		//"baseline": 0,
		//"calibrationStage" : "hello",
		//"objectsHit" : 0,
		//"rounds" : 0,
		//"score" : 0,
		//"speed" : 0,
		//"totalWords" : 0,
		//"totalWrongWords" : 0,
		//"userId" : "85965866-7b1b-41d8-a746-56f662d85bf1g",
		//"wordsCorrect" : 0
	std::string gameSessionHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/game-session");
	std::cout << "creating game session, whats the userId? (ex: fa305be6-c014-4744-bc1b-3ad539508b07)" << std::endl;
	std::string sessionNameId;
	std::cin >> sessionNameId;
	std::cout << "game session name is: " << sessionNameId;
	int score = 0;
	std::cout << std::endl;
	std::cout << "score?: ";
	std::cin >> score;
	std::string scoreStr = std::to_string(score);
	std::string scoreTemp = "\"score\":";
	std::string userIdGameSessionTemp("\"userId\":");
	std::string sessionNameIdQ = "\"" + sessionNameId + "\"";
	std::string gameSessionBody = "{" + userIdGameSessionTemp + sessionNameIdQ + ", " + scoreTemp + scoreStr + "}";

	std::cout << std::endl << std::endl;

	{
		auto r = cpr::Post(cpr::Url{ gameSessionHttpURL },
			cpr::Body{ gameSessionBody },
			//cpr::Body{ R"({"baseline":0, "calibrationStage":"nope", "objectsHit":0, "rounds":0, "score":0, "speed":0, "totalWords":0, "totalWrongWords":0, "userId": "INPUTNUMBER", "wordsCorrect":0})" },			//figure out how to input varible inside a string for userID
			cpr::Header{ { "Content-Type", "application/json" } }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl;

		//code: grabs UUID - need to change lngth of idname 
		std::string userInfo = r.text;
		std::cout << "TEST:" + userInfo << std::endl;
		//std::cout << typeid(userName).name() << std::endl; //find variable type
		std::string userIdTxt = "gameSessionId";
		int userIdStore = 0;
		userIdStore = userInfo.find(userIdTxt);
		int lenn = userInfo.length();
		std::cout << lenn << std::endl;
		std::cout << userIdStore << std::endl;
		char userInfoArr[37] = "";
		int j = 0;
		int userIdNameLen = 16;
		int UUIDLen = 37;
		int userIdNameLenMin1 = 15;
		for (int i = userIdStore + userIdNameLen; i < userIdStore + UUIDLen + userIdNameLenMin1; i++) {

			userInfoArr[j] = userInfo[i];
			j++;
			std::cout << userInfoArr[j];
		}
		j = 0;
		std::cout << std::endl;
		std::string deviceIdStr(userInfoArr);
		std::cout << "gameSessionId: ";
		std::cout << deviceIdStr << std::endl;
		//end of UUID
	}

	//4**********
	//NOTE: userId>deviceId>sessionId
	//info: faceroller
	//userId			fa305be6-c014-4744-bc1b-3ad539508b07
	//deviceId			b08b3db2-c34d-4368-a991-44b85d15a115
	//gameSessionId		15d01687-1457-4056-abca-4ac9019d6f73
	std::string positionHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/position-event");
	int x = 0;
	int y = 0;
	int z = 0;
	std::cout << "what is your deviceId?" << std::endl;
	std::string deviceIdPos;
	std::cin >> deviceIdPos;
	std::cout << "what is your game sessionId?" << std::endl;
	std::string gameSessionIdPos;
	std::cin >> gameSessionIdPos;
	std::cout << "what is your x?" << std::endl;
	std::cin >> x;
	std::cout << "what is your y?" << std::endl;
	std::cin >> y;
	std::cout << "what is your z?" << std::endl;
	std::cin >> z;
	std::cout << std::endl << std::endl;


	std::string deviceIdPosTemp("\"deviceId\":");
	std::string gameSessionIdPosTemp("\"gameSessionId\":");
	std::string xPosTemp("\"x\":");
	std::string yPosTemp("\"y\":");
	std::string zPosTemp("\"z\":");

	std::string xStr = std::to_string(x);
	std::string yStr = std::to_string(y);
	std::string zStr = std::to_string(z);

	std::string deviceIdPosQ = "\"" + deviceIdPos + "\"";
	std::string gameSessionIdQ = "\"" + gameSessionIdPos + "\"";
	std::string positionBody = "{" + deviceIdPosTemp + deviceIdPosQ + ", " + gameSessionIdPosTemp + gameSessionIdQ + ", " + xPosTemp + xStr + ", " + yPosTemp + yStr + ", " + zPosTemp + zStr + "}";
	std::cout << "posBody: " << positionBody << std::endl;

	{


		auto r = cpr::Post(cpr::Url{ positionHttpURL },
			//cpr::Body{ R"({"firstName":"hello" , "lastName":"world" , "username":"ItWorks"})" }
			//cpr::Body{ R"({"deviceId":"b08b3db2-c34d-4368-a991-44b85d15a115", "gameSessionId" : "15d01687-1457-4056-abca-4ac9019d6f73", "x" : 1, "y" : 2, "z" : 3})" },			//figure out how to input varible inside a string for userID
			cpr::Body{ positionBody },
			cpr::Header{ { "Content-Type", "application/json" } }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl;
	}

	//+++++++++++++++++++++++++++TEMPLATES++++++++++++++++++++++++++++

	//{
	//	std::cout << "HTTP Response to all users" << std::endl;
	//	auto r = cpr::Get(
	//		cpr::Url{ "http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/device/all" }
	//	);
	//	std::cout << "Returned Status:" << r.status_code << std::endl;
	//	std::cout << "Returned Text:" << r.text << std::endl;
	//	//if (r.status_code != 200)
	//	//{
	//	//	std::cout << "status code: " << r.status_code << std::endl;
	//	//}
	//}

	//std::cout << std::endl;

	//std::string whatisthis = "{\"username\": \"user3\" , \"firstName\":\"john2\" , \"lastName\":\"doe2\"}";
	//std::cout << whatisthis << std::endl;
	//{
	//	std::cout << "creating a user" << std::endl;
	//	auto r = cpr::Post(cpr::Url{ "http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user" },
	//		//cpr::Body{ R"({"firstName":"hello" , "lastName":"world" , "username":"ItWorks"})" }
	//		cpr::Body{ whatisthis },
	//		cpr::Header{ { "Content-Type", "application/json" } }
	//	);
	//	//cpr::Header{ { "Content-Type", "application/json" } });
	//	std::cout << "Returned Status:" << r.status_code << std::endl;
	//	std::cout << "Returned Text:" << r.text << std::endl;
	//}

	//std::cout << std::endl;

	//{
	//	std::cout << "returning the user info" << std::endl;
	//	auto r = cpr::Get(cpr::Url{ "http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user?username=user1" }
	//	);
	//	std::cout << "Returned Status:" << r.status_code << std::endl;
	//	std::cout << "Returned Text:" << r.text << std::endl;

	//}



	
	std::cout << std::endl;
	std::cout << "end of code" << std::endl;

	return 0;
}

