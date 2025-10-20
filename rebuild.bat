@echo off
echo ================================================================================
echo   Travel Optics - Complete Rebuild (Clean + Build)
echo ================================================================================
echo.

echo This will:
echo   1. Stop Gradle daemon
echo   2. Clean all caches
echo   3. Clean build output
echo   4. Rebuild from scratch
echo.
echo Press any key to continue or Ctrl+C to cancel...
pause >nul

call clean_cache.bat

echo.
echo ================================================================================
echo   Starting fresh build...
echo ================================================================================
echo.

call build.bat
