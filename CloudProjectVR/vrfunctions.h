#include "openvr.h"
#include <iostream>

using namespace std;
using namespace vr;

void Shutdown(IVRSystem* vr_pointer);
void RunProcedure(IVRSystem* vr_pointer);
HmdVector3_t getHMDPosition(IVRSystem* vr_pointer, ETrackedDeviceClass trackedDevice);
HmdVector3_t getControllerPosition(IVRSystem* vr_pointer, ETrackedDeviceClass trackedDevice, int deviceId);

void printPositionVector(HmdVector3_t positionVector);

void printHMDDeviceID(int deviceId);
void printControllerDeviceId(int deviceId);
