echo on
call setenv.bat
cd %TRUNK%\app\tzuyuEclipsePlugin
rem set up links for tzuyu reference.
mklink  /D tzuyuCore %TRUNK%\app\tzuyu\target\classes
mklink  /D tzuyuLib %TRUNK%\app\tzuyu\lib

rem set up links for icsetlv reference.
mklink  /D icsetlvCore %TRUNK%\app\icsetlv\target\classes
mklink  /D icsetlvLib %TRUNK%\app\icsetlv\lib

cd %TRUNK%\app\tzuyu.parent
rem call mvn clean install
rem call mvn eclipse:eclipse