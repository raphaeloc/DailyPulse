//
//  AboutScreen.swift
//  iosApp
//
//  Created by Rapha on 20/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationStack {
            AboutView()
                .navigationTitle("About Device")
                .toolbar {
                    ToolbarContentView(dismiss)
                }
        }
    }
}

@ToolbarContentBuilder
private func ToolbarContentView(_ dismiss: DismissAction) -> some ToolbarContent {
    ToolbarItem(placement: .primaryAction) {
        Button {
            dismiss()
        } label: {
            Text("Done")
                .bold()
        }
    }
}
