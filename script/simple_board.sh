#!/bin/sh
#
# ��ũ��Ʈ ����
#   - FTT_JVM_OPTS: ���ø����̼� ���࿡ �ʿ��� JVM �ɼ�
#   - FTT_APP_HOME: ���ø����̼��� ��ġ�� Ȩ ���
#   - FTT_APP_PORT: ���ø����̼� ���� ��Ʈ
#   - FTT_JVM_NAME: ���ø����̼��� �̸�
#   - FTT_APP_OPT_VAR_NAME: Gradle�� ���ؼ� �����Ǵ� ���� ��ũ��Ʈ�� �ɼ��� ������ ������ ����
#
# ��ũ��Ʈ ������ ���� �������� �ʴ� ��� ���丮 ��� ������� ó��
#   - ��� ����: [APP_USER_HOME]/[FTT_APP_NAME]/[FTT_APP_PORT]/
#       ex> /433/sample/12480
#           => 'sample'�� 'FTT_APP_NAME'�� ��
#           => '12480'�� 'FTT_APP_PORT'�� ��
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
