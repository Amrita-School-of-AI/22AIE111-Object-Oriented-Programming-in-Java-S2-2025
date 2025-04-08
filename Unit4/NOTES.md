# GUI Programming with Java Swing: Notes

## Table of Contents

1.  [Introduction to Swing](#1-introduction-to-swing)
2.  [Applets](#2-applets)
    - [The Applet/JApplet Class](#the-appletjapplet-class)
    - [Applet Lifecycle](#applet-lifecycle)
    - [Simple JApplet Example](#simple-japplet-example)
    - [Note on Modern Usage](#note-on-modern-usage)
3.  [The Delegation Event Model](#3-the-delegation-event-model)
    - [Core Concepts](#core-concepts)
    - [Events](#events)
    - [Event Sources](#event-sources)
    - [Event Listeners](#event-listeners)
    - [Event Classes](#event-classes)
    - [Registering Listeners](#registering-listeners)
    - [Event Handling Example](#event-handling-example)
4.  [Common Swing Components](#4-common-swing-components)
    - [`JLabel`](#jlabel)
    - [`JTextField`](#jtextfield)
    - [`JButton`](#jbutton)
    - [`JList`](#jlist)
    - [`JComboBox`](#jcombobox)
5.  [Mouse and Keyboard Events](#5-mouse-and-keyboard-events)
    - [Mouse Events (`MouseListener`, `MouseMotionListener`)](#mouse-events-mouselistener-mousemotionlistener)
    - [Keyboard Events (`KeyListener`)](#keyboard-events-keylistener)
6.  [Layout Managers (Brief Overview)](#6-layout-managers-brief-overview)
7.  [Putting It Together: Example Application](#7-putting-it-together-example-application)

---

## 1. Introduction to Swing

- **Swing** is a part of the Java Foundation Classes (JFC) and provides a rich toolkit for creating graphical user interfaces (GUIs) in Java.
- Swing components are written purely in Java, making them **lightweight** (compared to their AWT predecessors which relied on native OS components) and **platform-independent** (look and feel can be consistent across different operating systems).
- Swing follows a **Model-View-Controller (MVC)** architecture, separating data (Model), presentation (View), and user interaction logic (Controller).
- Core Swing components usually start with a 'J' (e.g., `JFrame`, `JPanel`, `JButton`, `JLabel`).
- Swing applications are typically built by creating a top-level container (like `JFrame`), adding panels (`JPanel`) for organization, and then placing components (`JButton`, `JTextField`, etc.) onto the panels or frame.

---

## 2. Applets

### The Applet/JApplet Class

- **Applets** were small Java programs designed to be embedded within HTML web pages and executed by a Java-enabled web browser.
- The original AWT-based applet class is `java.applet.Applet`.
- The Swing equivalent is `javax.swing.JApplet`, which should be used if you intend to use Swing components within an applet. `JApplet` extends `Applet` and provides a structure similar to `JFrame` (with a content pane).

### Applet Lifecycle

Applets have a defined lifecycle managed by the browser or applet viewer:

1.  **`init()`**: Called once when the applet is first loaded. Used for initialization (creating components, setting up resources).
2.  **`start()`**: Called after `init()` and every time the browser navigates back to the page containing the applet. Used to start animations, threads, etc.
3.  **`stop()`**: Called when the browser navigates away from the page. Used to stop animations, threads, etc.
4.  **`destroy()`**: Called when the applet is about to be unloaded permanently. Used for final cleanup.

### Simple JApplet Example

```java
import javax.swing.*;
import java.awt.*; // Still needed for layout managers like FlowLayout

/*
<applet code="SimpleApplet.class" width="300" height="100">
</applet>
*/

// Note: Applets are largely deprecated and may not run in modern browsers.
// This example is for educational purposes.

public class SimpleApplet extends JApplet {

    @Override
    public void init() {
        // Swing components should be added to the content pane
        // Use SwingUtilities.invokeLater for thread safety when creating GUI
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    setLayout(new FlowLayout()); // Set layout for the content pane
                    JLabel label = new JLabel("Hello from JApplet!");
                    add(label); // Add label to the content pane
                }
            });
        } catch (Exception e) {
            System.err.println("GUI creation failed: " + e);
        }
    }
}
```

- **To Run:** Compile (`javac SimpleApplet.java`) and create an HTML file referencing the `.class` file using an `<applet>` tag (as shown in the comment). Then, view the HTML file using the `appletviewer` tool (part of older JDKs) or a browser configured to support Java applets (increasingly rare).

### Note on Modern Usage

- Java Applets have been **deprecated** and largely removed from modern web browsers due to security concerns and the rise of alternative web technologies (like JavaScript, HTML5).
- For desktop applications, `JFrame` is the standard top-level container.

---

## 3. The Delegation Event Model

This is the standard mechanism for handling user interactions (like button clicks, mouse movements, key presses) in both AWT and Swing.

### Core Concepts

1.  **Event Source**: The component where the event originates (e.g., a `JButton`, a `JTextField`, the `JFrame` itself).
2.  **Event Object**: An object that encapsulates information about the event that occurred (e.g., an `ActionEvent` for a button click, a `MouseEvent` for a mouse action). It contains details like the source of the event.
3.  **Event Listener**: An object that "listens" for specific types of events from a specific source. It contains methods that are executed when the event occurs. Listeners must implement a corresponding listener interface (e.g., `ActionListener`, `MouseListener`).

### Flow

1.  A user interacts with a component (e.g., clicks a button) -> This is the **Event Source**.
2.  The source creates an **Event Object** describing the event.
3.  The source sends (delegates) this Event Object to all registered **Event Listeners** of the appropriate type.
4.  The listener executes the appropriate method to handle the event.

### Events

- Represent occurrences, typically user actions.
- Subclasses of `java.util.EventObject`. GUI events typically subclass `java.awt.AWTEvent`.

### Event Sources

- Any GUI component can be an event source.
- Examples: `JButton`, `JTextField`, `JList`, `JCheckBox`, `JFrame`, `JPanel`.

### Event Listeners

- Interfaces defined in `java.awt.event.*` or `javax.swing.event.*`.
- Define methods that receive event objects.
- Your code implements these interfaces to provide the event handling logic.

### Event Classes

- `java.awt.event.ActionEvent`: Represents a component-defined action (e.g., button click, menu item selection, pressing Enter in a `JTextField`, timer firing). Handled by `ActionListener`.
- `java.awt.event.MouseEvent`: Represents mouse actions (clicks, presses, releases, movement, entering/exiting component area). Handled by `MouseListener` and `MouseMotionListener`.
- `java.awt.event.KeyEvent`: Represents keyboard actions (key presses, releases, typing). Handled by `KeyListener`.
- `java.awt.event.ItemEvent`: Represents a change in state for an item (e.g., selecting/deselecting an item in a `JList` or `JComboBox`). Handled by `ItemListener`.
- `java.awt.event.WindowEvent`: Represents changes in a window's state (opening, closing, activation). Handled by `WindowListener`.
- ...and many others (`FocusEvent`, `ComponentEvent`, `ContainerEvent`, etc.)

### Registering Listeners

- An event source needs to be told which listener object(s) to notify.
- This is done using `add<EventType>Listener()` methods on the source component.
- Example: `myButton.addActionListener(myActionListenerObject);`
- Example: `myPanel.addMouseListener(myMouseListenerObject);`

### Event Handling Example

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Import event classes

public class EventDemo implements ActionListener { // Implement the listener interface

    JLabel label;

    public EventDemo() {
        JFrame frame = new JFrame("Event Handling Demo");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Create the Event Source
        JButton button = new JButton("Click Me");

        // 2. Create the Listener (this object implements ActionListener)
        // In this case, the EventDemo object itself is the listener.

        // 3. Register the Listener with the Source
        button.addActionListener(this); // 'this' refers to the EventDemo instance

        label = new JLabel("Button not yet clicked.");

        frame.add(button);
        frame.add(label);

        frame.setVisible(true);
    }

    // 4. Implement the listener method
    @Override
    public void actionPerformed(ActionEvent ae) {
        // This method is called when the button is clicked
        // 'ae' is the Event Object
        label.setText("Button was clicked!");
    }

    public static void main(String[] args) {
        // Ensure GUI creation happens on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventDemo();
            }
        });
    }
}
```

- **Alternative:** Often, **anonymous inner classes** are used for listeners, especially for simple actions:

```java
// Inside the constructor or setup method:
JButton anotherButton = new JButton("Show Message");
anotherButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Hello!"); // Show a simple dialog
    }
});
frame.add(anotherButton);
```

---

## 4. Common Swing Components

### `JLabel`

- **Purpose**: Displays read-only text, an image, or both. Not interactive by default (doesn't fire `ActionEvent`).
- **Key Constructors**:
  - `JLabel(String text)`
  - `JLabel(Icon image)`
  - `JLabel(String text, Icon image, int horizontalAlignment)`
- **Key Methods**:
  - `setText(String text)`: Changes the displayed text.
  - `setIcon(Icon image)`: Changes the displayed icon.
  - `getText()`: Retrieves the current text.
  - `getIcon()`: Retrieves the current icon.
- **Important**: A `JLabel` (or any component) must be added to a container (like a `JFrame` or `JPanel`) to be visible.

**Example:**

```java
import javax.swing.*;
import java.awt.*;

public class JLabelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JLabel Demo");
            frame.setLayout(new FlowLayout());
            frame.setSize(300, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a JLabel with text
            JLabel textLabel = new JLabel("This is text.");

            // Create a JLabel with text and specify alignment
            // (ImageIcon would require an actual image file)
            // For simplicity, we'll just use text here.
            JLabel centeredLabel = new JLabel("Centered Text", SwingConstants.CENTER);

            // Add labels to the frame's content pane
            frame.add(textLabel);
            frame.add(centeredLabel); // This label isn't created or added

            frame.setVisible(true);
        });
    }
}
```

### `JTextField`

- **Purpose**: Allows editing/displaying of a single line of text.
- **Key Constructors**:
  - `JTextField()`
  - `JTextField(int columns)`: Specifies preferred width.
  - `JTextField(String text)`: Initializes with text.
  - `JTextField(String text, int columns)`
- **Key Methods**:
  - `getText()`: Returns the text currently in the field.
  - `setText(String text)`: Sets the text in the field.
  - `setEditable(boolean editable)`: Controls whether the user can edit the text.
  - `addActionListener(ActionListener l)`: Adds a listener notified when Enter is pressed.
- **Events**: Fires `ActionEvent` when the user presses Enter.

**Example:**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTextFieldExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JTextField Demo");
            frame.setLayout(new FlowLayout());
            frame.setSize(350, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextField textField = new JTextField("Edit me", 20); // 20 columns wide
            JLabel feedbackLabel = new JLabel("Press Enter in the text field.");

            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = textField.getText();
                    feedbackLabel.setText("You entered: " + text);
                }
            });

            frame.add(new JLabel("Input:")); // Add a descriptive label
            frame.add(textField);
            frame.add(feedbackLabel);

            frame.setVisible(true);
        });
    }
}
```

### `JButton`

- **Purpose**: A standard clickable button. Triggers an action when clicked.
- **Key Constructors**:
  - `JButton(String text)`
  - `JButton(Icon icon)`
  - `JButton(String text, Icon icon)`
- **Key Methods**:
  - `setText(String text)`
  - `setIcon(Icon icon)`
  - `addActionListener(ActionListener l)`: **Crucial** for handling clicks.
- **Events**: Fires `ActionEvent` when clicked.

**Example:**

```java
import javax.swing.*;
import java.awt.event.*;

public class JButtonExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JButton Demo");
            JButton button = new JButton("Click Me");

            // Add the button to the frame's content pane
            frame.add(button); // Correct way to add a component

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Button Clicked!");
                }
            });

            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); // Make frame visible AFTER adding components
        });
    }
}
```

### `JList`

- **Purpose**: Displays a list of items from which the user can select one or more.
- **Key Constructors**:
  - `JList(Object[] listData)`: From an array.
  - `JList(Vector<?> listData)`: From a Vector.
  - `JList(ListModel<E> dataModel)`: Most flexible, using a data model.
- **Key Methods**:
  - `getSelectedValue()`: Gets the selected item.
  - `getSelectedIndex()`: Gets the index of the selected item.
  - `getSelectedValuesList()`: Gets a list of selected items (for multiple selections).
  - `setSelectionMode(int selectionMode)`: e.g., `ListSelectionModel.SINGLE_SELECTION`.
  - `addListSelectionListener(ListSelectionListener l)`: Handles selection changes.
- **Common Practice**: Often placed inside a `JScrollPane` to handle lists longer than the display area.
- **Events**: Fires `ListSelectionEvent` when the selection changes. Handled by `ListSelectionListener`.

**Example:**

```java
import javax.swing.*;
import javax.swing.event.*; // For ListSelectionListener
import java.awt.*;

public class JListExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JList Demo");
            frame.setLayout(new FlowLayout());
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String[] items = {"Apple", "Banana", "Cherry", "Date", "Fig", "Grape"};
            JList<String> list = new JList<>(items);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one selection

            // Put the list in a scroll pane
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setPreferredSize(new Dimension(100, 80)); // Set preferred size for scroll pane

            JLabel selectionLabel = new JLabel("Selected: None");

            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    // Check if this is the final event in a series
                    if (!e.getValueIsAdjusting()) {
                        String selected = list.getSelectedValue();
                        selectionLabel.setText("Selected: " + (selected != null ? selected : "None"));
                    }
                }
            });

            frame.add(scrollPane);
            frame.add(selectionLabel);
            frame.setVisible(true);
        });
    }
}
```

### `JComboBox`

- **Purpose**: A combination of a button or editable field and a drop-down list. Saves screen space compared to `JList`.
- **Key Constructors**:
  - `JComboBox(Object[] items)`: From an array.
  - `JComboBox(Vector<?> items)`: From a Vector.
  - `JComboBox(ComboBoxModel<E> aModel)`: Using a data model.
- **Key Methods**:
  - `getSelectedItem()`: Gets the currently selected item.
  - `getSelectedIndex()`: Gets the index of the selected item.
  - `addItem(E item)`: Adds an item to the list.
  - `addActionListener(ActionListener l)`: Fired when selection is finalized (often preferred).
  - `addItemListener(ItemListener l)`: Fired whenever the selection state _changes_.
- **Events**: Fires `ActionEvent` (selection final) and `ItemEvent` (selection changed).

**Example:**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JComboBoxExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JComboBox Demo");
            frame.setLayout(new FlowLayout());
            frame.setSize(300, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String[] choices = {"Option 1", "Option 2", "Option 3", "Option 4"};
            JComboBox<String> comboBox = new JComboBox<>(choices);

            JLabel choiceLabel = new JLabel("Chosen: Option 1");

            // Using ActionListener is often simpler for final selection
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String chosen = (String) comboBox.getSelectedItem();
                    choiceLabel.setText("Chosen: " + chosen);
                }
            });

            /* // Alternative using ItemListener (fires twice per click - deselect old, select new)
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String chosen = (String) e.getItem();
                        choiceLabel.setText("Chosen: " + chosen);
                    }
                }
            });
            */

            frame.add(new JLabel("Select:"));
            frame.add(comboBox);
            frame.add(choiceLabel);
            frame.setVisible(true);
        });
    }
}
```

---

## 5. Mouse and Keyboard Events

Handling direct input from the mouse and keyboard involves specific listeners.

### Mouse Events (`MouseListener`, `MouseMotionListener`)

- Used to respond to mouse actions over a component.
- **`MouseListener` Interface Methods**:
  - `mouseClicked(MouseEvent e)`: Invoked when the mouse button has been clicked (pressed and released) on a component.
  - `mousePressed(MouseEvent e)`: Invoked when a mouse button has been pressed on a component.
  - `mouseReleased(MouseEvent e)`: Invoked when a mouse button has been released on a component.
  - `mouseEntered(MouseEvent e)`: Invoked when the mouse cursor enters the bounds of a component.
  - `mouseExited(MouseEvent e)`: Invoked when the mouse cursor exits the bounds of a component.
- **`MouseMotionListener` Interface Methods**:
  - `mouseDragged(MouseEvent e)`: Invoked when a mouse button is pressed on a component and then dragged.
  - `mouseMoved(MouseEvent e)`: Invoked when the mouse cursor has been moved onto a component but no buttons are pressed.
- **Adapter Classes**: `MouseAdapter` provides empty implementations of `MouseListener`, `MouseMotionListener`, and `MouseWheelListener`. You can extend it and override only the methods you need.
- **`MouseEvent` Object**: Provides details like `getX()`, `getY()` (coordinates relative to the source component), `getButton()` (which button was pressed), `getClickCount()`.

**Example:**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventDemo extends MouseAdapter { // Extend MouseAdapter

    JLabel statusLabel;
    JFrame frame;

    public MouseEventDemo() {
        frame = new JFrame("Mouse Events Demo");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statusLabel = new JLabel("Move or click the mouse in this window.", SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH); // Add label at the bottom

        // Register 'this' object (which extends MouseAdapter) as the listener
        // for both mouse and mouse motion events on the frame itself.
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);

        frame.setVisible(true);
    }

    // Override methods from MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        statusLabel.setText("Mouse Clicked at (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        statusLabel.setText("Mouse Entered Window");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        statusLabel.setText("Mouse Exited Window");
    }

    // Override methods from MouseMotionListener
    @Override
    public void mouseMoved(MouseEvent e) {
        statusLabel.setText("Mouse Moved at (" + e.getX() + ", " + e.getY() + ")");
    }

     @Override
    public void mousePressed(MouseEvent e) {
        // Often useful to know when the press starts
         statusLabel.setText("Mouse Pressed at (" + e.getX() + ", " + e.getY() + ")");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MouseEventDemo::new);
    }
}
```

### Keyboard Events (`KeyListener`)

- Used to respond to keyboard input on a component.
- **Important**: The component must have **keyboard focus** to receive key events. Often added to `JFrame` or specific input components like `JTextField`. You might need `component.setFocusable(true);` and potentially `component.requestFocusInWindow();` for components that aren't naturally focusable.
- **`KeyListener` Interface Methods**:
  - `keyTyped(KeyEvent e)`: Invoked when a character key (like 'a', '!', '5') is pressed and released. Doesn't usually fire for action keys like Shift, F1, Enter. Use `e.getKeyChar()` to get the character.
  - `keyPressed(KeyEvent e)`: Invoked when _any_ key is pressed down. Use `e.getKeyCode()` to get the virtual key code (e.g., `KeyEvent.VK_ENTER`, `KeyEvent.VK_SHIFT`).
  - `keyReleased(KeyEvent e)`: Invoked when _any_ key is released. Use `e.getKeyCode()`.
- **Adapter Class**: `KeyAdapter` provides empty implementations.
- **`KeyEvent` Object**: Provides `getKeyChar()`, `getKeyCode()`, `isShiftDown()`, `isControlDown()`, etc.

**Example:**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventDemo extends KeyAdapter { // Extend KeyAdapter

    JLabel keyStatusLabel;
    JFrame frame;

    public KeyEventDemo() {
        frame = new JFrame("Key Events Demo");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        keyStatusLabel = new JLabel("Press keys while this window has focus.", SwingConstants.CENTER);
        frame.add(keyStatusLabel);

        // Add the KeyListener to the frame
        frame.addKeyListener(this);

        // Make the frame focusable to receive key events directly
        frame.setFocusable(true);
        // Optional: Request focus when window opens
        // frame.addWindowListener(new WindowAdapter() {
        //     @Override
        //     public void windowOpened(WindowEvent e) {
        //         frame.requestFocusInWindow();
        //     }
        // });


        frame.setVisible(true);
         // Request focus *after* frame is visible might be needed sometimes
        frame.requestFocusInWindow();
    }

    // Override methods from KeyListener (via KeyAdapter)
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String keyText = KeyEvent.getKeyText(keyCode); // Get descriptive text for the key
        keyStatusLabel.setText("Key Pressed: " + keyText + " (Code: " + keyCode + ")");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        // Only update if it's a printable character for demo clarity
        if (Character.isDefined(keyChar) && !Character.isISOControl(keyChar)) {
             keyStatusLabel.setText("Key Typed: '" + keyChar + "'");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         int keyCode = e.getKeyCode();
         String keyText = KeyEvent.getKeyText(keyCode);
         keyStatusLabel.setText("Key Released: " + keyText + " (Code: " + keyCode + ")");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KeyEventDemo::new);
    }
}
```

---

## 6. Layout Managers (Brief Overview)

- **Purpose**: Control the size and position of components within a container (`JFrame`, `JPanel`). Without a layout manager (or setting layout to `null`), you'd have to manually set the size and position of every component, which is tedious and doesn't adapt well to window resizing.
- **Setting Layout**: Use the `setLayout(LayoutManager)` method on the container. Example: `myPanel.setLayout(new FlowLayout());`
- **Common Layout Managers**:
  - `FlowLayout`: (Default for `JPanel`) Arranges components left-to-right, top-to-bottom, like words on a page.
  - `BorderLayout`: (Default for `JFrame`'s content pane) Divides the container into five regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, `CENTER`. Components added without constraint go to `CENTER`. Example: `frame.add(myButton, BorderLayout.SOUTH);`
  - `GridLayout`: Arranges components in a rectangular grid of equal-sized cells. `new GridLayout(rows, cols)`
  - `BoxLayout`: Arranges components in a single row (`BoxLayout.X_AXIS`) or a single column (`BoxLayout.Y_AXIS`). Respects component's preferred/max/min sizes more than `FlowLayout` or `GridLayout`. Often used with `Box.createVerticalStrut()` or `Box.createHorizontalGlue()` for spacing.
  - `GridBagLayout`: Most flexible and complex. Arranges components in a grid, but cells can span multiple rows/columns, and components can have different sizes and alignments. Uses `GridBagConstraints` object to specify constraints.
  - `CardLayout`: Shows only one component at a time, like a stack of cards.
  - `GroupLayout`: Designed for GUI builder tools but can be used manually. Uses `SequentialGroup` and `ParallelGroup`.
- **`null` Layout**: Setting layout to `null` (`container.setLayout(null);`) means you **must** manually set the size and position of each component using `setBounds(x, y, width, height)`. This is generally discouraged as it doesn't resize well.

```java
import javax.swing.*;
import java.awt.*; // Required for FlowLayout

public class PanelLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Example");
        JPanel panel = new JPanel(); // JPanel uses FlowLayout by default
        JButton button = new JButton("Submit");

        // Set the layout using setLayout method
        panel.setLayout(new FlowLayout()); // Correct way to set/change layout

        panel.add(button);
        frame.add(panel); // Add the panel (with its own layout) to the frame

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

---

## 7. Putting It Together: Example Application

This example combines several components and event handling.

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CombinedSwingExample {

    private JLabel feedbackLabel;
    private JTextField nameField;
    private JComboBox<String> colorComboBox;
    private JList<String> optionsList;

    public CombinedSwingExample() {
        // 1. Create the main Frame
        JFrame frame = new JFrame("Combined Swing Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        // Use BorderLayout for the frame's content pane
        frame.setLayout(new BorderLayout(10, 10)); // Gaps between regions

        // 2. Create Panels for organization
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // For name input
        JPanel centerPanel = new JPanel(); // Will hold list and combo box using BoxLayout
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical stack
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // For button and label

        // 3. Create Components
        // --- Top Panel Components ---
        topPanel.add(new JLabel("Name:"));
        nameField = new JTextField(15);
        topPanel.add(nameField);

        // --- Center Panel Components ---
        // ComboBox
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        colorComboBox = new JComboBox<>(colors);
        colorComboBox.setAlignmentX(Component.LEFT_ALIGNMENT); // Align in BoxLayout
        JLabel comboLabel = new JLabel("Favorite Color:");
        comboLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align in BoxLayout

        // List
        String[] options = {"Option A", "Option B", "Option C", "Option D"};
        optionsList = new JList<>(options);
        optionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        optionsList.setVisibleRowCount(3); // Show 3 rows at a time
        JScrollPane listScrollPane = new JScrollPane(optionsList);
        listScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // Align in BoxLayout
        JLabel listLabel = new JLabel("Select an Option:");
        listLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align in BoxLayout

        // Add to Center Panel with spacing
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        centerPanel.add(comboLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Vertical space
        centerPanel.add(colorComboBox);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Vertical space
        centerPanel.add(listLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Vertical space
        centerPanel.add(listScrollPane);


        // --- Bottom Panel Components ---
        JButton submitButton = new JButton("Submit Info");
        feedbackLabel = new JLabel("Enter information and click Submit.");
        bottomPanel.add(submitButton);
        bottomPanel.add(feedbackLabel);

        // 4. Add Panels to Frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // 5. Add Event Handling (ActionListener for the button)
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String color = (String) colorComboBox.getSelectedItem();
                String selectedOption = optionsList.getSelectedValue();

                if (name.trim().isEmpty()) {
                    feedbackLabel.setText("Please enter a name.");
                    return;
                }
                if (selectedOption == null) {
                    feedbackLabel.setText("Please select an option from the list.");
                    return;
                }

                feedbackLabel.setText("Hi " + name + "! Color: " + color + ", Option: " + selectedOption);
            }
        });

        // 6. Make the frame visible (usually last step)
        // frame.pack(); // Alternative to setSize - sizes frame based on component preferred sizes
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(CombinedSwingExample::new);
    }
}
```
