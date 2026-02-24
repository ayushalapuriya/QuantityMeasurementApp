📏 Quantity Measurement Application

A clean, modular, and extensible Java-based Quantity Measurement System built using strong object-oriented design principles and a Test-Driven Development (TDD) approach.

The project has been developed incrementally from UC1 to UC5, focusing on accurate unit comparison, reliable conversion logic, and arithmetic operations on value objects.

_____________________________________________________________________

🚀 Overview

This application currently supports:

✅ Reliable equality comparison between measurement units

🔄 Seamless unit conversion using a base-unit normalization strategy

➕ Addition of two length measurements (same category)

🎯 Result expressed in the unit of the first operand

🧪 JUnit-driven validation for every use case

The system is designed to be immutable, type-safe, and easily extensible for future measurement domains.

_____________________________________________________________________

📚 Supported Measurement Types
📏 Length

Feet

Inches

Yards

Centimeters

(Current implementation focuses on length comparison, conversion, and addition accuracy up to UC6.)

_____________________________________________________________________

🏗️ Design Highlights

🔹 Value Object Pattern – Quantity objects are immutable

🔹 Enum-based Unit Management – Centralized conversion logic

🔹 Base Unit Normalization – All calculations performed in a common base unit (Feet)

🔹 Floating-Point Precision Handling using epsilon comparison

🔹 Commutative and Identity-safe Arithmetic

🔹 Validation-first Design for nulls, invalid units, and non-finite values

_____________________________________________________________________

📈 Use Case Progression (UC1 – UC5)

Implemented incrementally to strengthen design and logic step-by-step:

UC1–UC2 → Basic equality checks

UC3–UC4 → Cross-unit comparison

UC5 → Robust conversion architecture with base unit strategy


Each use case was implemented using TDD — tests were written first, followed by minimal implementation to pass them.

_____________________________________________________________________

🧠 Key Technical Concepts Applied

Object-Oriented Design (OOP)

Immutability

Enum-based strategy pattern

Base-unit normalization

Method overloading for flexibility

Floating-point precision control

Exception-driven validation

JUnit 5 testing practices

_____________________________________________________________________

🎯 Learning Outcomes

This project strengthened practical understanding of:

Designing domain-driven value objects

Writing clean, testable Java code

Applying TDD in incremental feature development

Building scalable and extensible architecture

Maintaining mathematical correctness across unit conversions

_____________________________________________________________________

📌 Current Status

✔️ Completed up to UC5 (Addition of Length Units)
🚧 Future scope includes:

Subtraction, multiplication, division

Multi-domain support (Weight, Temperature, Volume)

Generic Quantity<T> abstraction

Production-grade architecture enhancements

_____________________________________________________________________

⭐ This project demonstrates strong fundamentals in Java design, abstraction, TDD discipline, and scalable system thinking.

