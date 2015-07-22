MAINTAINER andrew@recursivechaos.com

FROM dockerfile/java

ADD /opt/properties/dsbot-scraper /opt/properties/dsbot-scraper

COPY dsbot-scraper.jar .

RUN java -jar dsbot-scraper.jar -Dspring.config.location=file:/opt/properties/dsbot-scraper/