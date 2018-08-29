import subprocess
import sys


def throughput_avg(argv):

    throughput_list = []
    throughput_text = 'Throughput'
    throughput_line_index = 0

    default_iteration = 2
    max_iteration = default_iteration
    current_iteration = 0

    if len(argv) == 2:
        max_iteration = int(argv[1])
        print('max iteration: ' + str(max_iteration))

    while(current_iteration < max_iteration):

        current_iteration += 1
        output = subprocess.check_output("netperf", shell=True)

        # read response line by line. When we find a line with the word 'Throughput', we know that the value will be on the 4th line.   
        for line in output.split('\n'):
            #print (line)

            if throughput_line_index > 0:
                throughput_line_index +=  1

            if throughput_text in line:
                throughput_line_index = 1

            # the 4th line after we fonud 'Throughput' contains the it's value
            if throughput_line_index == 4:
                throughput = 0

                #collect the last value
                for col in line.split():
                    throughput = col
                throughput_list.append(float(throughput))

                #reset the throughput_line_index 
                throughput_line_index = 0
        
    print ('Average throughput :' + str(sum(throughput_list)/len(throughput_list)))

if __name__ == "__main__":
   throughput_avg(sys.argv)