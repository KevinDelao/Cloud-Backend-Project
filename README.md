# Cloud-Backend-Project

This repository contains the microservice dealing with the tracking data received from an HTC Vive. It's meant to expose REST API endpoints that allow for
storage and (maybe) retrieval of this data in a way compliant with HIPAA

# Starting Dockerfile
On windows, you'll need to enable permissions for docker user to write to shared drives

Create a new user (Computer Management → System Tools → Local Users and Groups → Users → Create new user), e.g. “DockerHost”. Give it a password.
Mark “User cannot change password” & “Password never expires” (in properties of that user).
Add it to “Administrators” group (also in properties of that user).
Share the drive where projects reside with the newly created user. i.e. C:/ (Properties of drive → sharing tab → Advanced sharing → Permissions → add the newly created user for docker → give it full access ). Repeat process for specific path to projects folder (for example of another user C:/Users/OtherUser/projects) and make sure it has permissions for full access. Sometimes this is handy for refreshing sharing and make it work.
Shared Drives in docker settings - Add user credentials to Docker for Windows, using the newly created user with its password.