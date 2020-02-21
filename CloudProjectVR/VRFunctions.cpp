#include "vrfunctions.h"

void Shutdown(IVRSystem* vr_pointer)
{
	if (vr_pointer != NULL)
	{
		VR_Shutdown();
		vr_pointer = NULL;
	}
}

void RunProcedure(IVRSystem* vr_pointer)
{
	VREvent_t event;
	if (vr_pointer->PollNextEvent(&event, sizeof(event)))
	{
		cout << "\nprocessing the event\n";
	}
	//Output tracking data or do other things
	cout << "\nDONE: RunProcedure\n";
}

HmdVector3_t getHMDPosition(IVRSystem* vr_pointer, ETrackedDeviceClass trackedDevice) {
	TrackedDevicePose_t trackedDevicePose;
	vr_pointer->GetDeviceToAbsoluteTrackingPose(TrackingUniverseStanding, 0, &trackedDevicePose, 1); //member or function GetDeviceToAbsoluteTrackingPose executes however I don't know what it does exactly....
				 //Pose is then stored in trackedDevicePose
					//if (trackedDevicePose.bPoseIsValid) {
					//	cout << "\n\nValid headset!\n\n";
					//}
	HmdMatrix34_t positionMatrix = trackedDevicePose.mDeviceToAbsoluteTracking;

	HmdVector3_t positionVector;
	positionVector.v[0] = positionMatrix.m[0][3];
	positionVector.v[1] = positionMatrix.m[1][3];
	positionVector.v[2] = positionMatrix.m[2][3];

	return positionVector;
}


void printPositionVector(HmdVector3_t positionVector) {
	printf("%f", positionVector.v[0]);
	cout << ' ';
	printf("%f", positionVector.v[1]);
	cout << ' ';
	printf("%f", positionVector.v[2]);
	cout << ' ';
}


void printHMDDeviceID(int deviceId) {
	cout << "\n\nHeadset XYZ Data Device ID: ";
	printf("%d", deviceId);
	cout << endl;
}

HmdVector3_t getControllerPosition(IVRSystem* vr_pointer, ETrackedDeviceClass trackedDevice, int deviceId) {
	TrackedDevicePose_t trackedDevicePose;
	VRControllerState_t controllerState;
	vr_pointer->GetControllerStateWithPose(TrackingUniverseStanding, deviceId, &controllerState, sizeof(controllerState), &trackedDevicePose);
	//if (trackedDevicePose.bPoseIsValid) {
	//	cout << "\n\nValid controller!\n\n";
	//}
	HmdMatrix34_t positionMatrix = trackedDevicePose.mDeviceToAbsoluteTracking;

	HmdVector3_t positionVector = trackedDevicePose.vVelocity; ;
	positionVector.v[0] = positionMatrix.m[0][3];
	positionVector.v[1] = positionMatrix.m[1][3];
	positionVector.v[2] = positionMatrix.m[2][3];

	return positionVector;
}

void printControllerDeviceId(int deviceId) {
	cout << "\n\nController XYZ Data Device ID: ";
	printf("%d", deviceId);
	cout << endl;
}