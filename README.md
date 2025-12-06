# 🌸 Real Sakura Rain (Java Animation)

![Preview](![SakuraRain](https://github.com/user-attachments/assets/3041336a-c868-4811-9372-e565409f00fa)
)

A detailed simulation of falling Cherry Blossoms, built entirely with **Java Swing & AWT**.

## 📝 Description
Unlike simple particle animations that use basic circles, this project procedurally draws **realistic, heart-shaped petals** using vector geometry. It simulates wind, gravity, and 3D-rotation effects to create a calming, atmospheric scene.

Every pixel is rendered via code—no external image files are used.

## ✨ Key Features
* **Custom Vector Shapes:** Uses `GeneralPath` and Bezier curves (`curveTo`) to draw the specific indented shape of a Sakura petal, rather than simple ovals.
* **Advanced Transformations:** Utilizes `AffineTransform` to handle rotation and scaling, creating a "pseudo-3D" effect as the petals flip and tumble in the wind.
* **Physics Simulation:** Each petal has independent variables for speed, spin, and sway (sine wave math), creating an organic, non-repetitive flow.
* **Visual Quality:** Implements High-Quality Rendering Hints (Anti-Aliasing) for smooth edges.

## 💻 Tech Stack & Concepts
* **Language:** Java
* **Graphics:** `java.awt.geom` (GeneralPath, AffineTransform)
* **GUI:** `javax.swing` (JPanel, Timer)
* **Math:** Trigonometry (`Math.sin`, `Math.cos`) for natural movement patterns.

## 🚀 How to Run
1.  Download `SakuraRain.java`.
2.  Compile and run via terminal or import into your IDE (Eclipse/IntelliJ):
    ```bash
    javac SakuraRain.java
    java SakuraRain
    ```

---
*Created by LumiAero88*
