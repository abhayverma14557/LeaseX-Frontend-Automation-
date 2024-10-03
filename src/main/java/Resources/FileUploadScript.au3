; Check if a file path is passed as a command-line argument
If $CmdLine[0] < 1 Then
    MsgBox(0, "Error", "File path not provided.")
    Exit
EndIf

; Store the file path from the first argument
$filePath = $CmdLine[1]

; Wait for the file upload dialog
WinWaitActive("Open")
Sleep(2500)

; Set the file path in the File Name input field
ControlSetText("Open", "", "Edit1", $filePath)

; Click the Open button
ControlClick("Open", "", "Button1")
Sleep(1000)
Exit
