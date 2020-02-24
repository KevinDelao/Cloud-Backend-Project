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

#include "openvr.h"

using namespace std;
using namespace vr;

void Shutdown(void);

IVRSystem* vr_pointer = NULL;

#include <iostream>
#include <string>
#include <typeinfo>
#include <cstring>

#include <dos.h>
#include <thread>
#include <chrono>
#include "windows.h"

int main()
{
	std::string userId = "";
	std::string deviceId = "";
	std::string gameSessionId = "";

	std::string deviceIdPosQ = "\"" + deviceId + "\"";

	std::string userName;
	std::string httpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user?username=");
	std::string userNameHttpURL = httpURL;
	int statusCode = 0;
	//1*******
	std::cout << "1-------------------------------------------------------" << std::endl;
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
			//std::cout << "TEST:" + userInfo << std::endl;
			//std::cout << typeid(userName).name() << std::endl; //find variable type
			std::string userIdTxt = "userId";
			int userIdStore = 0;
			userIdStore = userInfo.find(userIdTxt);
			int lenn = userInfo.length();
			//std::cout << lenn << std::endl;
			//std::cout << userIdStore << std::endl;
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
			userId = userIdStr;
			//end of UUID
		}

	}

	std::cout << std::endl;

	if(statusCode == 404){
		std::string createUserHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/user");
		//std::cout << "TEST: URL - " + createUserHttpURL << std::endl;
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
		//std::cout << "TEST:" + userInfo << std::endl;
		//std::cout << typeid(userName).name() << std::endl; //find variable type
		std::string userIdTxt = "userId";
		int userIdStore = 0;
		userIdStore = userInfo.find(userIdTxt);
		int lenn = userInfo.length();
		//std::cout << lenn << std::endl;
		//std::cout << userIdStore << std::endl;
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
		std::string userIdStr(userInfoArr);
		std::cout << "userId: ";
		std::cout << userIdStr << std::endl << std::endl;
		userId = userIdStr;
		//end of UUID
	
		
	}

	//2*******
	std::cout << "2-------------------------------------------------------" << std::endl;
	std::string deviceHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/device?name=");

	std::cout << "what is your device? (ex: james_device)" << std::endl;
	std::string deviceName;
	std::cin >> deviceName;
	std::cout << "device name is: " << deviceName << std::endl;
	//std::cout << "userId is: " << userId << std::endl;
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
			//std::cout << "TEST:" + userInfo << std::endl;
			//std::cout << typeid(userName).name() << std::endl; //find variable type
			std::string userIdTxt = "deviceId";
			int userIdStore = 0;
			userIdStore = userInfo.find(userIdTxt);
			int lenn = userInfo.length();
			//std::cout << lenn << std::endl;
			//std::cout << userIdStore << std::endl;
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
			std::cout << "deviceId: " + deviceIdStr << std::endl;
			deviceId = deviceIdStr;
			//end of UUID

		}
	}


	if (statusCode == 404) {
		
		std::cout << "Device doesnt exist, using input to create a device." << std::endl;
		std::cout << "creating a device with input: " << deviceName << std::endl << std::endl;
		//std::cout << "what is your userId?" << std::endl;
		//std::cin >> userId;
		std::cout << "using userId: " + userId << std::endl;
		
		std::string deviceNameTemp("\"name\":");
		std::string deviceNameQ = "\"" + deviceName + "\"";
		std::string deviceUserIdTemp("\"userId\":");
		std::string userIdQ = "\"" + userId + "\"";
		std::string deviceBody = "{" + deviceNameTemp + deviceNameQ + ", " + deviceUserIdTemp + userIdQ + "}";
		
		auto r = cpr::Post(cpr::Url{ deviceHttpURL },

			cpr::Body{ deviceBody },
			cpr::Header{ { "Content-Type", "application/json" } }
		);
		std::cout << "Returned Status:" << r.status_code << std::endl;
		std::cout << "Returned Text:" << r.text << std::endl << std::endl;

		//code: grabs UUID - need to change lngth of idname 
		std::string userInfo = r.text;
		//std::cout << "TEST:" + userInfo << std::endl;
		//std::cout << typeid(userName).name() << std::endl; //find variable type
		std::string userIdTxt = "deviceId";
		int userIdStore = 0;
		userIdStore = userInfo.find(userIdTxt);
		int lenn = userInfo.length();
		//std::cout << lenn << std::endl;
		//std::cout << userIdStore << std::endl;
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
		std::cout << "deviceId: " + deviceIdStr << std::endl;
		deviceId = deviceIdStr;

		//end of UUID
	}

	//3**********
	std::cout << "3-------------------------------------------------------" << std::endl;
	std::string gameSessionHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/game-session");
	//std::cout << "creating game session, whats the userId? (ex: fa305be6-c014-4744-bc1b-3ad539508b07)" << std::endl;
	std::cout << "creating game session with userId: " + userId << std::endl;
	//std::string sessionNameId;
	//std::cin >> sessionNameId;
	//std::cout << "game session name is: " << sessionNameId;
	int score = 100;
	//std::cout << std::endl;
	//std::cout << "score?: ";
	//std::cin >> score;
	std::string scoreStr = std::to_string(score);
	std::string scoreTemp = "\"score\":";
	std::string userIdTemp("\"userId\":");
	std::string userIdQ = "\"" + userId + "\"";
	std::string gameSessionBody = "{" + userIdTemp + userIdQ + ", " + scoreTemp + scoreStr + "}";

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
		//std::cout << "TEST:" + userInfo << std::endl;
		//std::cout << typeid(userName).name() << std::endl; //find variable type
		std::string userIdTxt = "gameSessionId";
		int userIdStore = 0;
		userIdStore = userInfo.find(userIdTxt);
		int lenn = userInfo.length();
		//std::cout << lenn << std::endl;
		//std::cout << userIdStore << std::endl;
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
		std::string gameSessionIdStr(userInfoArr);
		std::cout << "gameSessionId: ";
		std::cout << gameSessionIdStr << std::endl;
		gameSessionId = gameSessionIdStr;
		//end of UUID
	}

	//4**********
	std::cout << "4-------------------------------------------------------" << std::endl;
	//NOTE: userId>deviceId>sessionId
	//info: faceroller
	//userId			fa305be6-c014-4744-bc1b-3ad539508b07
	//deviceId			b08b3db2-c34d-4368-a991-44b85d15a115
	//gameSessionId		15d01687-1457-4056-abca-4ac9019d6f73
	std::string positionHttpURL("http://vr-rehab-cloud-service-usc-dev.us-east-2.elasticbeanstalk.com/v1/position-event");
	int x = 100;
	int y = 300;
	int z = 9000;
	std::string xx;
	std::string yy;
	std::string zz;
	xx = x;
	yy = y;
	zz = z;

	//VIVE*************************************************************
	//Create Error Object
	EVRInitError eError = VRInitError_None;

	//Initializing VR System
	vr_pointer = VR_Init(&eError, VRApplication_Background);
	int tryAgain = 1;
	char input;

	//Catching any Initialzing Errors
	if (eError != VRInitError_None)
	{
		vr_pointer = NULL;
		printf("Unable to init VR runtime: %s \n",
			VR_GetVRInitErrorAsEnglishDescription(eError));
		exit(EXIT_FAILURE);
	}

	//Need to get Device ID and choose what device ID belongs to what?  Maybe I dont
	//while (tryAgain) {
	while(1){

		Sleep(1000);

		cout << "\n--------------------------------------------------------------";

		//initialize controllers
		//controllers[0].deviceId = -1;
		//controllers[1].deviceId = -1;
		//controllers[0].hand = -1;
		//controllers[1].hand = -1;
		int numTrackersInitialized = 0;
		int numControllersInitialized = 0;


		for (unsigned int deviceIdv = 0; deviceIdv < k_unMaxTrackedDeviceCount; deviceIdv++) { //k_unMaxTrackedDeviceCount = 64

			TrackedDevicePose_t trackedDevicePose;
			VRControllerState_t controllerState;

			//print out what the track device is
			ETrackedDeviceClass trackedDevice = vr_pointer->GetTrackedDeviceClass(deviceIdv); //trackedDevice is now set to whatever vr_point member points to
			//cout << trackedDevice << endl;

			if (!vr_pointer->IsTrackedDeviceConnected(deviceIdv))
				continue;


			if (trackedDevice == ETrackedDeviceClass::TrackedDeviceClass_HMD) {

				vr_pointer->GetDeviceToAbsoluteTrackingPose(TrackingUniverseStanding, 0, &trackedDevicePose, 1); //member or function GetDeviceToAbsoluteTrackingPose executes however I don't know what it does exactly....
			 //Pose is then stored in trackedDevicePose
				//if (trackedDevicePose.bPoseIsValid) {
				//	cout << "\n\nValid headset!\n\n";
				//}
				HmdMatrix34_t positionMatrix = trackedDevicePose.mDeviceToAbsoluteTracking;

				cout << "\n\nHeadset XYZ Data Device ID: ";
				printf("%d", deviceIdv);
				cout << endl;
				printf("%f", positionMatrix.m[0][3]);
				cout << ' ';
				printf("%f", positionMatrix.m[1][3]);
				cout << ' ';
				printf("%f", positionMatrix.m[2][3]);
				cout << ' ';

				cout << "\n\nNew code to decipher position?\n";
				HmdVector3_t vector = trackedDevicePose.vVelocity; ;
				vector.v[0] = positionMatrix.m[0][3];
				vector.v[1] = positionMatrix.m[1][3];
				vector.v[2] = positionMatrix.m[2][3];
				printf("%f", vector.v[0]);
				cout << ' ';
				printf("%f", vector.v[1]);
				cout << ' ';
				printf("%f", vector.v[2]);
				cout << ' ';

				//HTTP____________________________________________________________________________


				std::string deviceIdPosTemp("\"deviceId\":");
				std::string gameSessionIdPosTemp("\"gameSessionId\":");
				std::string xPosTemp("\"x\":");
				std::string yPosTemp("\"y\":");
				std::string zPosTemp("\"z\":");

				float px;
				float py;
				float pz;

				px = positionMatrix.m[0][3];
				py = positionMatrix.m[1][3];
				pz = positionMatrix.m[2][3];

				std::string xStr = std::to_string(px);
				std::string yStr = std::to_string(py);
				std::string zStr = std::to_string(pz);

				std::cout << "creating a position event with: " << std::endl;
				std::cout << "deviceId: " + deviceIdv << std::endl;
				std::cout << "gameSessionId: " + gameSessionId << std::endl;
				std::cout << "with position x: " + xStr << std::endl;
				std::cout << "with position y: " + yStr << std::endl;
				std::cout << "with position z: " + zStr << std::endl;
				std::cout << std::endl << std::endl;

				std::string deviceIdPosQ = "\"" + deviceId + "\"";
				std::string gameSessionIdQ = "\"" + gameSessionId + "\"";
				std::string positionBody = "{" + deviceIdPosTemp + deviceIdPosQ + ", " + gameSessionIdPosTemp + gameSessionIdQ + ", " + xPosTemp + xStr + ", " + yPosTemp + yStr + ", " + zPosTemp + zStr + "}";
				std::cout << "posBody: " << positionBody << std::endl;

				{
					auto r = cpr::Post(cpr::Url{ positionHttpURL },
						cpr::Body{ positionBody },
						cpr::Header{ { "Content-Type", "application/json" } }
					);
					std::cout << "Returned Status:" << r.status_code << std::endl;
					std::cout << "Returned Text:" << r.text << std::endl;
				}
				//HTTP_____________________________________________________________

				//renamed trackedDevice to trackedDeviceClass
			}

			if (trackedDevice == ETrackedDeviceClass::TrackedDeviceClass_Controller && numControllersInitialized < 2) {
				vr_pointer->GetControllerStateWithPose(TrackingUniverseStanding, deviceIdv, &controllerState, sizeof(controllerState), &trackedDevicePose);
				//if (trackedDevicePose.bPoseIsValid) {
				//	cout << "\n\nValid controller!\n\n";
				//}


				HmdMatrix34_t positionMatrix = trackedDevicePose.mDeviceToAbsoluteTracking;

				cout << "\n\nController XYZ Data Device ID: ";
				printf("%d", deviceIdv);
				cout << endl;
				printf("%f", positionMatrix.m[0][3]);
				cout << ' ';
				printf("%f", positionMatrix.m[1][3]);
				cout << ' ';
				printf("%f", positionMatrix.m[2][3]);
				cout << ' ';

				cout << "\n\nNew code to decipher position?\n";
				HmdVector3_t vector = trackedDevicePose.vVelocity; ;
				vector.v[0] = positionMatrix.m[0][3];
				vector.v[1] = positionMatrix.m[1][3];
				vector.v[2] = positionMatrix.m[2][3];
				printf("%f", vector.v[0]);
				cout << ' ';
				printf("%f", vector.v[1]);
				cout << ' ';
				printf("%f", vector.v[2]);
				cout << ' ';

				

			}

		}

		//cout << "\n\nTry Again? (y/n)";
		//cin >> input;

		//if (input == 'n') {
		//	tryAgain = 0;
		//}


	}



	Shutdown();
	//VIVE*************************************************************





	//std::cout << "creating a position event with: " << std::endl;
	//std::cout << "deviceId: " + deviceId << std::endl;
	//std::cout << "gameSessionId: " + gameSessionId << std::endl;
	//std::cout << "with position x: " + xx << std::endl;
	//std::cout << "with position y: " + yy << std::endl;
	//std::cout << "with position z: " + zz << std::endl;
	//std::cout << std::endl << std::endl;


	//std::string deviceIdPosTemp("\"deviceId\":");
	//std::string gameSessionIdPosTemp("\"gameSessionId\":");
	//std::string xPosTemp("\"x\":");
	//std::string yPosTemp("\"y\":");
	//std::string zPosTemp("\"z\":");

	//std::string xStr = std::to_string(x);
	//std::string yStr = std::to_string(y);
	//std::string zStr = std::to_string(z);

	//std::string xStr = std::to_string(px);
	//std::string yStr = std::to_string(py);
	//std::string zStr = std::to_string(pz);

	//std::string deviceIdPosQ = "\"" + deviceId + "\"";
	//std::string gameSessionIdQ = "\"" + gameSessionId + "\"";
	//std::string positionBody = "{" + deviceIdPosTemp + deviceIdPosQ + ", " + gameSessionIdPosTemp + gameSessionIdQ + ", " + xPosTemp + xStr + ", " + yPosTemp + yStr + ", " + zPosTemp + zStr + "}";
	//std::cout << "posBody: " << positionBody << std::endl;

	//{
	//	auto r = cpr::Post(cpr::Url{ positionHttpURL },
	//		cpr::Body{ positionBody },
	//		cpr::Header{ { "Content-Type", "application/json" } }
	//	);
	//	std::cout << "Returned Status:" << r.status_code << std::endl;
	//	std::cout << "Returned Text:" << r.text << std::endl;
	//}

	std::cout << std::endl;
	std::cout << "end of code" << std::endl;

	while (1) {

	}

	return 0;
}

//Shutdown function, can also use VR_Shutdown()
void Shutdown()
{
	if (vr_pointer != NULL)
	{
		VR_Shutdown();
		vr_pointer = NULL;
	}
}



void RunProcedure()
{
	VREvent_t event;
	if (vr_pointer->PollNextEvent(&event, sizeof(event)))
	{
		cout << "\nprocessing the event\n";
	}
	//Output tracking data or do other things
	cout << "\nDONE: RunProcedure\n";
}

