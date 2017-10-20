#!/bin/bash
#
# This script is based on security/keycloak example from wildfly swarm examples:
# https://github.com/wildfly-swarm/wildfly-swarm-examples/tree/master/security/keycloak
#

if [ -z $1 ]
then
  echo "Please provide keycloak URL"
  exit 1
fi

RESULT=$(curl --data "grant_type=password&client_id=curl&username=admin&password=admin" http://$1/auth/realms/bjug/protocol/openid-connect/token 2>/dev/null)
echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'

