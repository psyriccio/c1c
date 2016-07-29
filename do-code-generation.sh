#!/bin/bash
xjc -d ./src/ -p c1c.meta.generated -xmlschema -nv -verbose -extension ./schema/conf.xsd
