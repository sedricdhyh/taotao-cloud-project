from __future__ import absolute_import, unicode_literals
from celery import shared_task

import time 
@shared_task
def add(x, y):
    print("running task add",x,y )
    time.sleep(10)
    return x + y


@shared_task
def mul(x, y):
    return x * y


@shared_task
def xsum(numbers):
    return sum(numbers)
