# Login
echo $DOCKER_PASSWORD | helm registry login docker.io -u $DOCKER_USERNAME --password-stdin

# Package and push
helm package helm-app/
helm push helm-app-0.1.0.tgz oci://docker.io/jeremydavis
