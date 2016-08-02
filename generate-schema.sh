#!/bin/bash
trang ./schema/conf.xml ./schema/conf.xsd
cat ./schema/conf.xsd | grep -v "<?xml" | grep -v "<xs:schema" > ./schema/conf.xsd.content
rm ./schema/conf.xsd
cat ./schema/conf.xsd.head > ./schema/conf.xsd
cat ./schema/conf.xsd.content >> ./schema/conf.xsd
