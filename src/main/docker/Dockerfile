FROM centos
MAINTAINER phoenix<xxx@126.com>

ENV MYPATH /usr/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80
CMD echo $MYPATH
CMD echo "my centos success!"
CMD /bin/bash
