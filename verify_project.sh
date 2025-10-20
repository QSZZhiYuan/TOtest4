#!/bin/bash

echo "=================================="
echo "  TravelOptics 模组项目验证"
echo "=================================="
echo ""

# 检查源代码目录
if [ -d "src/main/java" ]; then
    echo "✓ 源代码目录存在"
    JAVA_FILES=$(find src/main/java -name "*.java" | wc -l)
    echo "  Java文件数量: $JAVA_FILES"
else
    echo "✗ 源代码目录不存在"
fi

echo ""

# 检查混淆名称
echo "检查残留混淆名称..."
if command -v python3 &> /dev/null; then
    OBFUSCATED=$(grep -r "m_[0-9]\+_\|f_[0-9]\+_" src/main/java --include="*.java" 2>/dev/null | wc -l)
    if [ "$OBFUSCATED" -eq 0 ]; then
        echo "✓ 没有发现混淆名称 (m_xxx_, f_xxx_)"
    else
        echo "⚠ 发现 $OBFUSCATED 个混淆名称残留"
    fi
else
    echo "⚠ Python未安装，跳过混淆检查"
fi

echo ""

# 检查Gradle配置
if [ -f "build.gradle" ]; then
    echo "✓ Gradle构建文件存在"
    if [ -f "gradlew" ]; then
        echo "✓ Gradle包装器存在"
    fi
else
    echo "✗ Gradle构建文件不存在"
fi

echo ""

# 显示项目信息
if [ -f "replit.md" ]; then
    echo "项目信息:"
    grep -A 5 "## 反混淆处理结果" replit.md 2>/dev/null || echo "  (项目文档可用)"
fi

echo ""
echo "=================================="
echo "项目验证完成！"
echo ""
echo "下一步操作："
echo "  1. 运行 './gradlew build' 编译项目"
echo "  2. 使用IDE（IntelliJ IDEA）打开项目"
echo "  3. 运行 './gradlew runClient' 启动客户端"
echo "=================================="
