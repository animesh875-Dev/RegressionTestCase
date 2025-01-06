ARG DockerimageInheritFrom=artifactory.boschdevcloud.com/eet-docker-develop-local/osd6:latest
FROM $DockerimageInheritFrom as base

ARG PIP_PROXY=rb-proxy-unix-de01.bosch.com:8080
ARG DockerUSER=Smith
ARG DockerUID=1984
ARG DockerGID=1984
ARG DockerPW=docker

# Setup linux machine
ENV \
    # Configure machine enccoding
    LANG=C.UTF-8 \
    LC_ALL=C.UTF-8 \
    # set timezone for packages that need this information (openssh-server)
    TZ="Europe/Berlin"

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# install python3, pip3 and pipenv
RUN \
    apt-get update && apt-get install -y --no-install-recommends \
        python3=3.8.2-0ubuntu2 \
        python3-pip>=20.0.2-5ubuntu1.6 \
        default-jre \
        openjdk-11-jdk \
        maven \
        wget \
        zip \
    && \
    rm -rf /var/lib/apt/lists/* && \
    python3 -m pip install pipenv && \
    update-alternatives --install /usr/bin/python python /usr/bin/python3 1
COPY ./Pipfile ./Pipfile.lock ./
RUN chmod 777 ./Pipfile ./Pipfile.lock
RUN pipenv install --system --deploy
# setup container user equal to host user
RUN \
    groupadd --gid=${DockerGID} ${DockerUSER} && \
    # Remark: it is important to use '--no-log-init'.
    # without, long user-ids (e.g. in osd) will cause docker crash and to fill up all disk space
    useradd --no-log-init --create-home ${DockerUSER} --uid=${DockerUID} --gid=${DockerGID} && \
    echo "${DockerUSER}:${DockerPW}" | chpasswd && \
    usermod -aG sudo ${DockerUSER}

ENV MAVEN_HOME=/opt/maven

USER ${DockerUID}:${DockerGID}
WORKDIR /home/${DockerUSER}
# change shell
SHELL ["/bin/bash", "-euxo", "pipefail", "-c"]
# Run bash if no other command is provided
ENTRYPOINT ["/bin/bash", "-il", "-c"]
CMD ["/bin/bash", "-il"]