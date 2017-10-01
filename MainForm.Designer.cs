namespace Server_Switcher
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.mainTableLayout = new System.Windows.Forms.TableLayoutPanel();
            this.startGame = new System.Windows.Forms.PictureBox();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.propertiesButton = new System.Windows.Forms.Button();
            this.serverCombo = new System.Windows.Forms.ComboBox();
            this.mainTableLayout.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.startGame)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // mainTableLayout
            // 
            this.mainTableLayout.BackColor = System.Drawing.Color.Transparent;
            this.mainTableLayout.ColumnCount = 5;
            this.mainTableLayout.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.mainTableLayout.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 205F));
            this.mainTableLayout.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.mainTableLayout.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 205F));
            this.mainTableLayout.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.mainTableLayout.Controls.Add(this.startGame, 3, 1);
            this.mainTableLayout.Controls.Add(this.tableLayoutPanel1, 1, 1);
            this.mainTableLayout.Dock = System.Windows.Forms.DockStyle.Fill;
            this.mainTableLayout.Location = new System.Drawing.Point(0, 0);
            this.mainTableLayout.Name = "mainTableLayout";
            this.mainTableLayout.RowCount = 3;
            this.mainTableLayout.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.mainTableLayout.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 60F));
            this.mainTableLayout.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 25F));
            this.mainTableLayout.Size = new System.Drawing.Size(784, 441);
            this.mainTableLayout.TabIndex = 0;
            // 
            // startGame
            // 
            this.startGame.Dock = System.Windows.Forms.DockStyle.Fill;
            this.startGame.Image = global::Server_Switcher.Properties.Resources.play_default;
            this.startGame.Location = new System.Drawing.Point(554, 356);
            this.startGame.Margin = new System.Windows.Forms.Padding(0);
            this.startGame.Name = "startGame";
            this.startGame.Size = new System.Drawing.Size(205, 60);
            this.startGame.TabIndex = 0;
            this.startGame.TabStop = false;
            this.startGame.MouseDown += new System.Windows.Forms.MouseEventHandler(this.startGame_MouseDown);
            this.startGame.MouseEnter += new System.EventHandler(this.startGame_MouseEnter);
            this.startGame.MouseLeave += new System.EventHandler(this.startGame_MouseLeave);
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.propertiesButton, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.serverCombo, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(25, 356);
            this.tableLayoutPanel1.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 3;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel1.Size = new System.Drawing.Size(205, 60);
            this.tableLayoutPanel1.TabIndex = 1;
            // 
            // propertiesButton
            // 
            this.propertiesButton.Dock = System.Windows.Forms.DockStyle.Fill;
            this.propertiesButton.Location = new System.Drawing.Point(3, 34);
            this.propertiesButton.Name = "propertiesButton";
            this.propertiesButton.Size = new System.Drawing.Size(199, 23);
            this.propertiesButton.TabIndex = 0;
            this.propertiesButton.Text = "Properties";
            this.propertiesButton.UseMnemonic = false;
            this.propertiesButton.UseVisualStyleBackColor = false;
            // 
            // serverCombo
            // 
            this.serverCombo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.serverCombo.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.serverCombo.FormattingEnabled = true;
            this.serverCombo.Location = new System.Drawing.Point(3, 3);
            this.serverCombo.Name = "serverCombo";
            this.serverCombo.Size = new System.Drawing.Size(199, 21);
            this.serverCombo.TabIndex = 1;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoValidate = System.Windows.Forms.AutoValidate.Disable;
            this.BackgroundImage = global::Server_Switcher.Properties.Resources.background;
            this.ClientSize = new System.Drawing.Size(784, 441);
            this.Controls.Add(this.mainTableLayout);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Server Switcher";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.mainTableLayout.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.startGame)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel mainTableLayout;
        private System.Windows.Forms.PictureBox startGame;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Button propertiesButton;
        private System.Windows.Forms.ComboBox serverCombo;
    }
}

