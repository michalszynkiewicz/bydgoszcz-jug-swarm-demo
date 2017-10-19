#!/bin/bash

oc delete deploymentconfig apples
oc delete route apples
oc delete svc apples
