# -*- coding: utf-8 -*-
"""
Created on Sun Aug 19 16:06:25 2018

@author: Diego
"""

import cv2
import time

class CameraInst():
        # Constructor...
        
        def __init__(self):
                fps        = 20.0               # Frames per second...
                resolution = (640, 480)         # Frame size/resolution...

                self.cap = cv2.VideoCapture(0)  # Capture Video...
                print("Camera warming up ...")
                time.sleep(1)

                # Define the codec and create VideoWriter object
                fourcc = cv2.VideoWriter_fourcc(*"H264")     # You also can use (*'XVID')
                self.out = cv2.VideoWriter('output.avi',fourcc, fps, resolution)

        def captureVideo(self):
                # Capture
                self.ret, self.frame = self.cap.read()
                # Image manipulations come here...
                self.gray = cv2.cvtColor(self.frame, cv2.COLOR_BGR2GRAY)
                cv2.imshow('frame',self.gray)

        def saveVideo(self):
                # Write the frame
                self.out.write(self.frame)
                
#        def saveCompleteVideo(self):
#                #save 10 seconds of video to a folder
#                self.out = cv2.VideoWriter('output' + i + '.avi', NULL, fps, resolution)
#                #colocar for aqui
#                self.captureVideo()
#                self.saveVideo()

        def __del__(self):
                self.cap.release()
                cv2.destroyAllWindows()
                print("Camera disabled and all output windows closed...")
                
#def main2():
#        cam1 = CameraInst()
#        i = 0
#        while(True):
#               cam1.saveCompleteVideo(i)
#               if cv2.waitKey(1) & 0xFF == ord('q'):
#                        break
#               i+=1
#        cleanUp()
#        
        

def main():
        cam1 = CameraInst()

        while(True):
                # Display the resulting frames...
                cam1.captureVideo()    # Live stream of video on screen...
                cam1.saveVideo()       # Save video to file 'output.avi'...
                if cv2.waitKey(1) & 0xFF == ord('q'):
                        break

        cleanUp()

if __name__=='__main__':
        main()