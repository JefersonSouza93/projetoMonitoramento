import cv2
import time
import subprocess
import sys

def countPeople():
    while True:
        try:
            last = time.time()

            cap = cv2.VideoCapture('http://teste:teste123456@189.33.33.220:3333/video/mjpg.cgi')

            ret, frame = cap.read()

            # Convert to RGB
            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB);
            
            cv2.imwrite('cam.png', frame)
            
            cmd = "./darknet detect cfg/yolov3.cfg yolov3.weights cam.png" # optional flag: -thresh .2"

            output = subprocess.check_output(cmd.split())

            output = output.decode("utf-8").split("\n")
            
            # Count the number of lines that contain "person"
            numPeople = len([i.split(":")[0] for i in output if i.split(":")[0] == 'person'])

            # print(output[0])
            print("{} - {} people detected.".format(time.strftime("%d %b %Y %H:%M:%S", time.localtime()), numPeople))

            return numPeople

        # On keyboard interrupt, terminate program
        except KeyboardInterrupt:
            print("Program exiting")
            break

        # If an unknown exception occurs, print it and continue looping.
        except:
            print(sys.exc_info()[0])
            continue
    print("Waiting 5 minutes")
    time.sleep(300)