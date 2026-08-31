# Linux Study Guide

## Common Commands
for space separated names either wrap in single/double quotes or use an escape character with the white space. Linux does not rely on file extensions to determine file type. A file named `script.sh` and a file named `script.txt` are both just "files" unless applications choose to interpret them differently. Directories are a special type of filesystem object, but everything in Linux is generally treated as a file or file-like object.

### ls (list files/directories)
```cli
ls [options] [location]
```

### cd (change directory)
```cli
cd {location}
```

### pwd (print working directory)
```cli
pwd
```

### man (manual)
"q" will exit the manual
```cli
man {command}
```

### mkdir(make directory)
```cli
mkdir [options] {directory name}
```

### rmdir (remove directory)
rmdir removes empty directories only. If the directory contains files or subdirectories, the command will fail. To remove a directory and all its contents, use rm -r or the safer rm -ri (will prompt you for each file being deleted).
```cli
rmdir [options] {directory name}
```

### touch (create file)
this command does more than just create files, but it can be used to do so
```cli
touch [options] {file name}
```

### cp (copy)
```cli
cp [option] {source path} {destination path}
```

### mv (move)
note that moving the file to the same location with a new name will just rename the file
```cli
mv [option] {starting file} {new file}
```

### rm (remove)
NOTE: THERE IS NO UNDO OPTION, DELETE FILES AT YOUR PERIL
```cli
rm [options] <file>
```

### cat (concatenate)
```cli
cat <file>
```

### grep (tool for searching for patterns in lines of one or more files)
```grep
grep [option] {pattern} {file/s}
```

### cut
useful if you've made columns in your file: can indicate which field (column) you want shown and the character/s used to separate the columns. There are other options with this command
```cli
cut -f {column number, separate multiple via comma} -d [separator, tab is default when -f present] {path/to/file or content}
```

## Redirecting

### > (save to file/overwrite)
Redirects standard output (stdout) to a file. If the file does not exist it will be created. If it already exists, its contents will be overwritten.
```cli
{command/s} > {output file}
```

### >> (redirect to file)
double greater than operators will append the content to a file if it exists instead of saving over the previous content. If the file does not exist it will be created.
```cli
{command/s} >> {output file}
```

### | (piping)
A pipe sends the standard output (stdout) of one command directly to the standard input (stdin) of another command.
```cli
echo "1 2 3 4 5" | cut -f 1 -d " " # will return 1
```

### Standard Streams
Linux processes communicate through three standard data streams. These streams are used to receive input and send output or error messages between commands, the terminal, and files.
```
0 = stdin  (standard input)
1 = stdout (standard output)
2 = stderr (standard error)
```

## OS commands

### top
this command returns an overview of system resources being used and processes running, and will look something like the example below. Keys to know:
- Tasks: Total number of processes on the system, including running, sleeping, stopped, and zombie processes.
- Cpu(/s): cpu usage
- MiB Mem & Swap: Ram and virtual memory usage
- PID: process identifier. Useful for ending processes
- User: tells us who owns the process
```cli
top - 10:44:41 up 12 min,  0 users,  load average: 0.52, 0.58, 0.59
Tasks:   4 total,   1 running,   3 sleeping,   0 stopped,   0 zombie
%Cpu(s):  5.5 us,  3.3 sy,  0.0 ni, 89.6 id,  0.0 wa,  1.6 hi,  0.0 si,  0.0 st
MiB Mem :  16232.1 total,   9072.9 free,   6935.2 used,    224.0 buff/cache
MiB Swap:  30365.0 total,  30326.6 free,     38.4 used.   9166.2 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    1 root      20   0    8948    400    328 S   0.0   0.0   0:00.07 init
    8 root      20   0    9304    236    180 S   0.0   0.0   0:00.00 init
    9 revature  20   0   17320   4108   4004 S   0.0   0.0   0:00.29 bash
  124 revature  20   0   18820   2248   1588 R   0.0   0.0   0:00.10 top
```

### ps
this command will show the current working processes. Alone it doesn't provide much detail
```cli
ps
  PID TTY          TIME CMD
    9 tty1     00:00:00 bash
  144 tty1     00:00:00 ps
```
you can add aux to the command then we will get far more detail. A typical practice is to pipe the grep command to find any processes that are causing system issues.
```cli
ps aux
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0   8948   400 ?        Ssl  10:32   0:00 /init
root         8  0.0  0.0   9304   236 tty1     Ss   10:32   0:00 /init
revature     9  0.0  0.0  17320  4108 tty1     S    10:32   0:00 -bash
revature   143  0.0  0.0  18808  2116 tty1     R    10:49   0:00 ps -aux
```

### kill
A simple way to kill a process is to use the kill command and provide the PID number of the process. By default the command sends the signal "15" (SIGTERM), you can think of this as a "polite" way of requesting the process end
```cli
kill {PID}
```
This does not always work: when this process fails you can send signal "9" (SIGTERM) with the command: this is the force quit command, and you should always try the default kill command before this one
```cli
kill -9 {PID}
```
Keep in mind regular users can only kill processes they own: the root user may kill all processes

### jobs
Displays jobs started from the current shell session that are running or stopped in the foreground or background.

### & (move to background)
Adding "&" to the end of a command starts the command in the background. which allows you to continue working in the terminal you are in. This is useful if starting an app, running a command that will take a long time to process, or if the operation is intended to work in the background. 
You can also manually move a process to the background by pressing CTR + Z to suspend the current process and give you access to the shell again,  and then run the command "bg".

### fg (foreground)
this command will move a process from the background to the foreground: Note that this command takes the process id found from the "jobs" command instead of a PID, and it should be prefixed with "%
```cli
fg %{ID from jobs}
```

# Variables
variables are set by giving a name, equal sign (no space between name and =), then the value of the variable (no space between = and value)
```cli
variable_name=variable_value
```
to reference a variable name you use a $
```cli
echo $variable_name
```

when you need to use white space in a variable you can either use single or double quotes: Single quotes cause Bash to treat everything inside them literally. Variables, command substitutions, and most special characters are not expanded. Double quotes preserve whitespace while still allowing variable expansion and command substitution.
```cli
name='Ted'

using_single='Hello $name'
echo "$using_single" # will return Hello $name

using_double="Hello $name"
echo "$using_double" # will return Hello Ted
```
To store the output of a command in a variable, use command substitution with $().
```cli
content=$(ls)
```

### (())
Arithmetic expansion uses the syntax $((expression)), which evaluates the expression and substitutes its result.
```cli
sum=$((12+3))
```

## Control Flow
### if
if statements can be used to control the flow of your script's execution. it checks a logical condition, if if the condition is met the code associated with the if statement is executed
```cli
# single condition
if [ {condition to test} ]
then
    {commands to execute}
fi

# if else
if [ {condition to test} ]
then
    {commands to execute}
else
    {commands to execute}
fi

# if elif else
if [ {condition to test} ]
then
    {commands to execute}
elif [ {condition to test} ]
then
    {commands to execute}
else # optional
    {commands to execute}
fi
```
anything between "then" and "fi" is executed if the condition of the if statement is met. There are many operators that can be used in the [] (this is actually a reference to the command test) but here are common ones:
- !{expression}
    - condition is passed if the expression is false
- -n {string value}
    - returns true of the length of the string is greater than 0
- -z {string value}
    - returns true if the length of the string is 0
- {string one} = {string two}
    - returns true if both strings are equal (same characters)
    - use != to return true if they do not have the same characters
- {num one} -eq {num two}
    - returns true if the two numbers are equal
- {num one} -gt {num two}
    - returns true if num one is greater than num two
- {num one} -lt {num two}
    - returns true if num one is less than num two
- {num one} -ne {num two}
    - returns true if the numbers are not equal
- {num one} -ge {num two}
    - returns true if num one is greater or equal to num two
- {num one} -le {num two}
    - returns true if num one is less than or equal to num two
- -d {file}
    - file exists and is a directory
- -f {file}
    - file exists and is a regular file
- -e {file}
    - file exists
- -r {file}
    - file exists and has read permission
- -s {file}
    - file exists and has a size greater than 0 (not empty)
- -w {file}
    - file exists and has write permission
- -x {file}
    - file exists and has execute permission

remember to use "=" with string comparisons and -eq for numeric comparisons.

indentation does not have an effect on scripts, but it is good practice to indent your scripts so the structure of the whole is easier to understand. This is a common practice across scripting and programming langauges that can afford to do it.

### boolean operators
"&&" is the "and" operator, "||" is the "or" operator. These can be used to chain tests together
```cli
if [ {first condition to test} ] && [ {second condition to test} ]
then
    {commands to execute}
else
    {commands to execute}
fi
```

### for loops
for loops iterate through lists: the default delimiter for a list  spaces, tabs, and newlines, but you can change this by setting the internal field separator IFS value in your script to something else
```cli
IFS="{desired delimiter}"
for {reference to current iteration} in {list}
do
    {commands to execute}
done
```
there are a few different ways you can select a list
- provide list of strings
    - for name in $names
- use the content of a file as the iteration list
    - for name in $(cat names.txt)
        - note: this can work, but if the text is irregular it may break your loop
- provide a range to iterate through
    - for num in {{starting number}..{ending number}..{iteration through range}}
    - could also just put start and stop: for num in {{start}..{stop}}
- iterate through content in a directory
    - for file in {directory}/*

Note: "name", "num", and "file" in the examples above are temporary variables that hold the current value of the data for each iteration of the respective loop.