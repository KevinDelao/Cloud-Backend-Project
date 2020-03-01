#include "curlfunctions.h"

int getStatusCode(User myUser) {
	auto r = cpr::Get(cpr::Url{ myUser.getNameHttpURL() }
	);
	std::cout << "Returned Status:" << r.status_code << std::endl;
	std::cout << "Returned Text:" << r.text << std::endl << std::endl;
	return r.status_code;
}