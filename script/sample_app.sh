#!/bin/sh
#
# 스크립트 변수
#   - FTT_JVM_OPTS: 어플리케이션 실행에 필요한 JVM 옵션
#   - FTT_APP_HOME: 어플리케이션이 위치한 홈 경로
#   - FTT_APP_PORT: 어플리케이션 실행 포트
#   - FTT_JVM_NAME: 어플리케이션의 이름
#   - FTT_APP_OPT_VAR_NAME: Gradle에 의해서 생성되는 실행 스크립트로 옵션을 전달할 변수명 네임
#
# 스크립트 변수의 값을 변경하지 않는 경우 디렉토리 경로 기반으로 처리
#   - 경로 형식: [APP_USER_HOME]/[FTT_APP_NAME]/[FTT_APP_PORT]/
#       ex> /433/sample/12480
#           => 'sample'이 'FTT_APP_NAME'이 됨
#           => '12480'이 'FTT_APP_PORT'가 됨
#
#

FTT_JVM_OPTS="-Xms100m -Xmx100m -server"

FTT_APP_HOME=$(dirname $(dirname $(readlink -f $0)))
FTT_APP_PORT=$(basename $FTT_APP_HOME)
FTT_APP_NAME=$(basename $(dirname ${FTT_APP_HOME}))

FTT_APP_OPT_VAR_NAME="${FTT_APP_NAME^^}_OPTS"

PIDFILE=~/.pid/${FTT_APP_NAME}.${FTT_APP_PORT}.pid

mkdir -p $(dirname $PIDFILE)

case "$1" in
start)
    printf "%-50s" "Starting ${FTT_APP_NAME}..."
    if [ -f $PIDFILE ]; then
        PID=$(cat $PIDFILE)
        if [ -n "`ps axf | grep $PID | grep ${FTT_APP_NAME} | grep -v grep`" ]; then
            echo "Already running"
            exit
        fi
    fi

    cd $FTT_APP_HOME
    declare "${FTT_APP_OPT_VAR_NAME}=-Djava.security.egd=file:/dev/./urandom -Dserver.port=${FTT_APP_PORT} -Dspring.config.location=file:${FTT_APP_HOME}/config/application.properties ${FTT_JVM_OPTS}"
    export "${FTT_APP_OPT_VAR_NAME}"
    ./bin/${FTT_APP_NAME}&

    echo $! > $PIDFILE
    printf "%s\n" "Ok"
;;

status)
    printf "%-50s" "Checking ${FTT_APP_NAME}..."
    if [ -f $PIDFILE ]; then
        PID=$(cat $PIDFILE)
        if [ -z "`ps axf | grep $PID | grep ${FTT_APP_NAME} | grep -v grep`" ]; then
            printf "%s\n" "Process not found"
        else
            echo "Running"
        fi
    else
        printf "%s\n" "Service not running"
    fi
;;

stop)
    printf "%-50s" "Stopping ${FTT_APP_NAME}"
    if [ -f $PIDFILE ]; then
        PID=$(cat $PIDFILE)
        if [ -z "`ps axf | grep $PID | grep ${FTT_APP_NAME} | grep -v grep`" ]; then
            printf "%s\n" "Process not found"
        else
            kill -9 $PID
            printf "%s\n" "Ok"
        fi
    else
        printf "%s\n" "pidfile not found"
    fi
;;

restart)
        $0 stop
        sleep 3
        $0 start $2
;;



*)
    echo "Usage:"
    echo "  $0 start"
    echo "  $0 restart"
    echo "  $0 stop"
    echo "  $0 status"

esac
